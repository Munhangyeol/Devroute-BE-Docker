package com.teamdevroute.devroute.video;


import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_FRONT_KEY;
import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_FRONT_VALUE;
import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_MAX_RESULT;
import static com.teamdevroute.devroute.video.constans.ApiConstans.UDEMY_API_URL_SEARCH;
import static com.teamdevroute.devroute.video.constans.ApiConstans.YOUTUBE_API_URL_FRONT_VIDEOID;
import static com.teamdevroute.devroute.video.constans.ApiConstans.YOUTUBE_API_URL_SEARCH;
import static com.teamdevroute.devroute.video.enums.PlatformName.Youtube;

import com.teamdevroute.devroute.video.dto.udemy.UdemyApiResponse;
import com.teamdevroute.devroute.video.dto.udemy.UdemyVideoDTO;
import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse;
import com.teamdevroute.devroute.video.dto.youtube.YoutubeVideoDTO;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Collectors;
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

    @Value("${youtube.api.key}")
    private String youtubeApiKey;
    @Value("${udemy.api.clientId}")
    private String udemyApiClientId;
    @Value("${udemy.api.key}")
    private String udemyApiKey;

   private YouTubeApiResponse response;

    public VideoService(VideoRepository videoRepository, RestTemplate restTemplate) {
        this.videoRepository = videoRepository;
        this.restTemplate = restTemplate;
    }
    public void fetchAndSaveVideo() {
//        restTemplate = new RestTemplate();
//        fetchAndSaveYoutubeVideos();
        fetchAndSaveUdemyVideos();
    }
   public void fetchAndSaveYoutubeVideos() {
        for (TechnologyStackName value : TechnologyStackName.values()) {
            response = restTemplate.getForObject(getYoutubeApiUrl(value), YouTubeApiResponse.class);
           if(response!=null) {
               saveYoutubeVideo(response, value);
           }
           }
    }

    //해당 메서드는 리펙토링 예정입니다!
    public void fetchAndSaveUdemyVideos() {
//        RestTemplate restTemplate = new RestTemplate();
        String url = UDEMY_API_URL_SEARCH + "?search=" + "python"+"&fields[course]=title,url,price,image_125_H";;

        HttpHeaders headers = new HttpHeaders();
        String auth = udemyApiClientId + ":" + udemyApiKey;
        System.out.println("clientId: "+udemyApiClientId+udemyApiKey);
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.US_ASCII));
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UdemyApiResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, UdemyApiResponse.class);

        System.out.println(response.getBody().getResults().stream().map(course -> UdemyVideoDTO.builder()
                        .url(course.getUrl())
                        .title(course.getTitle())
                        .thumbnailUrl(course.getImage_125_H())
                        .price(Long.valueOf(course.getPrice().replaceAll("[^\\d]", ""))))
                .collect(Collectors.toList()));
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
//                System.out.println(videoId + " " + videoUrl + " " + thumbnailUrl);
                videoRepository.save(new YoutubeVideoDTO(videoUrl, title, thumbnailUrl).toEntity(
                        String.valueOf(Youtube), String.valueOf(teck_stack), 0L,++rank));
            }
        }

    }
    private String getYoutubeApiUrl(TechnologyStackName value) {
        return YOUTUBE_API_URL_SEARCH + QUERY_FRONT_VALUE + value + QUERY_FRONT_KEY + youtubeApiKey + QUERY_MAX_RESULT;
    }
//    private String getUdemyAPiUrl(TechnologyStackName value){
//        return
//    }
}
