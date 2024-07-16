package com.teamdevroute.devroute.video;


import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_FRONT_KEY;
import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_FRONT_VALUE;
import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_MAX_RESULT;
import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_UDEMY_SET_FEILD;
import static com.teamdevroute.devroute.video.constans.ApiConstans.UDEMY_API_URL_FRONT_VIDEOID;
import static com.teamdevroute.devroute.video.constans.ApiConstans.UDEMY_API_URL_SEARCH;
import static com.teamdevroute.devroute.video.constans.ApiConstans.YOUTUBE_API_URL_FRONT_VIDEOID;
import static com.teamdevroute.devroute.video.constans.ApiConstans.YOUTUBE_API_URL_SEARCH;
import static com.teamdevroute.devroute.video.enums.PlatformName.Udemy;
import static com.teamdevroute.devroute.video.enums.PlatformName.Youtube;

import com.teamdevroute.devroute.crawling.InfreanVideoCrawling;
import com.teamdevroute.devroute.video.dto.infrean.InfreanVideoDTO;
import com.teamdevroute.devroute.video.dto.udemy.UdemyApiResponse;
import com.teamdevroute.devroute.video.dto.udemy.UdemyApiResponse.Course;
import com.teamdevroute.devroute.video.dto.udemy.UdemyVideoDTO;
import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse;
import com.teamdevroute.devroute.video.dto.youtube.YoutubeVideoDTO;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final RestTemplate restTemplate;

    private final InfreanVideoCrawling infreanVideoCrawling;

    @Value("${youtube.api.key}")
    private String youtubeApiKey;
    @Value("${udemy.api.clientId}")
    private String udemyApiClientId;
    @Value("${udemy.api.key}")
    private String udemyApiKey;

   private YouTubeApiResponse response;

    public VideoService(VideoRepository videoRepository, RestTemplate restTemplate,
                        InfreanVideoCrawling infreanVideoCrawling) {
        this.videoRepository = videoRepository;
        this.restTemplate = restTemplate;
        this.infreanVideoCrawling = infreanVideoCrawling;
    }
    public void fetchAndSaveVideo() throws IOException {
//        fetchAndSaveYoutubeVideos();
//        fetchAndSaveUdemyVideos();
        fetchAndSaveInfreanVideos();
    }
   public void fetchAndSaveYoutubeVideos() {
        for (TechnologyStackName value : TechnologyStackName.values()) {
            response = restTemplate.getForObject(getYoutubeApiUrl(value), YouTubeApiResponse.class);
           if(response!=null) {
               saveYoutubeVideo(response, value);
           }
           }
    }
    public void fetchAndSaveUdemyVideos() {
        HttpHeaders headers = new HttpHeaders();
        setHeaderAuthBeforeFetchUdemyApi(headers);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        for(TechnologyStackName value: TechnologyStackName.values()){
            ResponseEntity<UdemyApiResponse> response = restTemplate.exchange(getUdemyApiUrl(value), HttpMethod.GET, entity, UdemyApiResponse.class);

            if(response!=null && response.getBody()!=null) {
                saveUdemyVideo(response.getBody(), value);
            }
        }
    }
    public void fetchAndSaveInfreanVideos() throws IOException {
        for(TechnologyStackName value: TechnologyStackName.values()){
            ArrayList<InfreanVideoDTO> infreanVideoDTOS = infreanVideoCrawling.crawlingInfreanVideo(value);
            saveInfreanVideo(infreanVideoDTOS,value);
        }

    }

    private void setHeaderAuthBeforeFetchUdemyApi(HttpHeaders headers) {
        String auth = udemyApiClientId + ":" + udemyApiKey;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.US_ASCII));
        headers.set("Authorization", "Basic " + encodedAuth);
    }

    private void saveYoutubeVideo(YouTubeApiResponse response, TechnologyStackName teck_stack){
        Long rank= Long.valueOf(0);
        for (YouTubeApiResponse.Item item : response.getItems()) {
            if(item.getId()==null)
                continue;
            String videoId = item.getId().getVideoId();
            String videoUrl = YOUTUBE_API_URL_FRONT_VIDEOID+videoId;
            String title = item.getSnippet().getTitle();
            String thumbnailUrl = item.getSnippet().getThumbnails().getDefault().getUrl();
            if (videoId != null && title != null && thumbnailUrl != null) {
                videoRepository.save(new YoutubeVideoDTO(videoUrl, title, thumbnailUrl).toEntity(
                        String.valueOf(Youtube), String.valueOf(teck_stack), 0L,++rank));
            }
        }

    }
    private void saveUdemyVideo(UdemyApiResponse response,TechnologyStackName teck_stack){
        Long rank= Long.valueOf(0);
        int currentCourseNumber = 0;
        for (Course course : response.getResults()) {
            String videoUrl= UDEMY_API_URL_FRONT_VIDEOID+course.getUrl();
            String title = course.getTitle();
            String thumnailUrl = course.getImage_125_H();
            Long price= Long.valueOf(course.getPrice().replaceAll("[^\\d]", ""));
            if(course.getUrl()!=null && title!=null && thumnailUrl!=null && price!=null) {
                videoRepository.save(
                        new UdemyVideoDTO(videoUrl, title, thumnailUrl, price).toEntity(String.valueOf(Udemy),
                                String.valueOf(teck_stack), 0L, ++rank));
                currentCourseNumber += 1;
            }
            if(currentCourseNumber>=10){
                break;
            }
        }
    }
    private void saveInfreanVideo(ArrayList<InfreanVideoDTO> infreanVideoDTOS,TechnologyStackName teck_stack){
        Long rank= Long.valueOf(0);
        for (InfreanVideoDTO infreanVideoDTO : infreanVideoDTOS) {
            videoRepository.save(infreanVideoDTO.toEntity("Infrean",
                    String.valueOf(teck_stack), 0L, rank));

        }

    }
    private String getYoutubeApiUrl(TechnologyStackName value) {
        return YOUTUBE_API_URL_SEARCH + QUERY_FRONT_VALUE + value + QUERY_FRONT_KEY + youtubeApiKey + QUERY_MAX_RESULT;
    }
    private String getUdemyApiUrl(TechnologyStackName value) {
        return UDEMY_API_URL_SEARCH  + value +QUERY_UDEMY_SET_FEILD;
    }
}
