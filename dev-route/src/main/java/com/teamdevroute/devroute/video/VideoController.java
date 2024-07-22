package com.teamdevroute.devroute.video;

import java.io.IOException;
import java.util.List;

import com.teamdevroute.devroute.video.dto.LectureResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @ResponseBody
    @GetMapping("/fetch-and-save")
    public String  fetchAndSaveYoutubeVideo() throws IOException {
        videoService.fetchAndSaveVideo();
        return "!!";
    }

    @ResponseBody
    @GetMapping("/lecture")
    public List<LectureResponseDTO> getRecommendLectureList(@RequestParam("platform_name") String platform_name
    ,@RequestParam("tech_name")String tech_name){
        return videoService.findLectureListByPlatformNameAndTechStack(platform_name, tech_name);
    }

}
