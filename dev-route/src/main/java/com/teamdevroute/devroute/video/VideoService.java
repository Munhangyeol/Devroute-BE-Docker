package com.teamdevroute.devroute.video;


import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_FRONT_KEY;
import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_FRONT_VALUE;
import static com.teamdevroute.devroute.video.constans.ApiConstans.QUERY_MAX_RESULT;
import static com.teamdevroute.devroute.video.constans.ApiConstans.YOUTUBE_API_URL_FRONT_VIDEOID;
import static com.teamdevroute.devroute.video.constans.ApiConstans.YOUTUBE_API_URL_SEARCH;
import static com.teamdevroute.devroute.video.enums.PlatformName.Youtube;

import com.teamdevroute.devroute.video.dto.YouTubeApiResponse;
import com.teamdevroute.devroute.video.dto.YoutubeVideoDTO;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final RestTemplate restTemplate;

    @Value("${youtube.api.key}")
    private String youtubeApiKey;

   private YouTubeApiResponse response;

    public VideoService(VideoRepository videoRepository, RestTemplate restTemplate) {
        this.videoRepository = videoRepository;
        this.restTemplate = restTemplate;
    }
    public void fetchAndSaveVideo() {
//        restTemplate = new RestTemplate();
        fetchAndSaveYoutubeVideos();
    }
   public void fetchAndSaveYoutubeVideos() {
        for (TechnologyStackName value : TechnologyStackName.values()) {
            response = restTemplate.getForObject(getYoutubeApiUrl(value), YouTubeApiResponse.class);
           if(response!=null) {
               saveYoutubeVideo(response, value);
           }
           }
    }
    private void saveYoutubeVideo(YouTubeApiResponse response, TechnologyStackName teck_stack){
        Long rank= Long.valueOf(0);
        for (YouTubeApiResponse.Item item : response.getItems()) {
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
}
