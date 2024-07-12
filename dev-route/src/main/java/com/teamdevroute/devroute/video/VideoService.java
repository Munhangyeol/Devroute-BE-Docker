package com.teamdevroute.devroute.video;


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
    private RestTemplate restTemplate;

    @Value("${youtube.api.key}")
    private String youtubeApiKey;
    @Value("${youtube.api.url}")
    private String youtubeApiUrl;

    private String entireUrl;

   private YouTubeApiResponse response;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;

    }
    public void fetchAndSaveVideo() {
        restTemplate = new RestTemplate();
        fetchAndSaveYoutubeVideos();
    }
    public void fetchAndSaveYoutubeVideos() {
        for (TechnologyStackName value : TechnologyStackName.values()) {
            entireUrl = youtubeApiUrl + "part=snippet&type=video" + "&q=" +value + "&key=" + youtubeApiKey + "&maxResults=10";
            response = restTemplate.getForObject(entireUrl, YouTubeApiResponse.class);
            saveYoutubeVideo(response,value);
        }
    }
    public void saveYoutubeVideo(YouTubeApiResponse response, TechnologyStackName teck_stack){
        for (YouTubeApiResponse.Item item : response.getItems()) {
            String videoId = item.getId().getVideoId();
            String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
            String title = item.getSnippet().getTitle();
            String thumbnailUrl = item.getSnippet().getThumbnails().getDefault().getUrl();
            if (videoId != null && title != null && thumbnailUrl != null) {
//                System.out.println(videoId + " " + videoUrl + " " + thumbnailUrl);
                videoRepository.save(new YoutubeVideoDTO(videoUrl, title, thumbnailUrl).toEntity(
                        String.valueOf(Youtube), String.valueOf(teck_stack)));
            }
        }

    }
}
