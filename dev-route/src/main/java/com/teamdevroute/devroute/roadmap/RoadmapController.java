package com.teamdevroute.devroute.roadmap;

import com.teamdevroute.devroute.roadmap.dto.DetailedRoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.dto.RoadmapResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoadmapController {
    private final RoadmapService  roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }
    @ResponseBody
    @GetMapping("/roadmap/{develpmentField}")
    public List<RoadmapResponseDTO> getRoadMap(@PathVariable("develpmentField") String develpmentField){
        return roadmapService.findByDevelpmentField(develpmentField);
    }
    @ResponseBody
    @GetMapping("/roadmap/{develpmentField}/{stepsName}")
    public DetailedRoadmapResponseDTO getDetailedRoadMap(@PathVariable("develpmentField") String develpmentField
    , @PathVariable("stepsName") String stepsName){
        return roadmapService.findByDevelpmentFieldAndStepsName(develpmentField, stepsName);
    }

    //이 메서드는 api프로토콜에 포함된것이 아님. 처음에 update만
    @ResponseBody
    @GetMapping("/roadmap/update")
    public String updateRoadMap(){
        roadmapService.updateAllRoadMap();
        return "sucessfull update roadmap";
    }
}
