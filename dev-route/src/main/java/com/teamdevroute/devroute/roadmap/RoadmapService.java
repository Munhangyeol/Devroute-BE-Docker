package com.teamdevroute.devroute.roadmap;

import com.teamdevroute.devroute.roadmap.dto.DetailedRoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.dto.RoadmapResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RoadmapService {
    public RoadmapResponseDTO findByDevelpmentField(String develpmentField) {
        return new RoadmapResponseDTO();
    }

    public DetailedRoadmapResponseDTO findByDevelpmentFieldAndStepsName(String develpmentField, String stepsName) {
        return new DetailedRoadmapResponseDTO();
    }
}
