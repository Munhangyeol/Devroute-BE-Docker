package com.teamdevroute.devroute.roadmap;

import com.teamdevroute.devroute.roadmap.domain.RoadmapStep;
import com.teamdevroute.devroute.roadmap.dto.DetailedRoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.dto.RoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.repository.RoadmapStepRepository;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.teamdevroute.devroute.roadmap.description.Ai.stepsAiBriefNames;
import static com.teamdevroute.devroute.roadmap.description.Ai.stepsAiNames;
import static com.teamdevroute.devroute.roadmap.description.Backend.stepBackendBriefNames;
import static com.teamdevroute.devroute.roadmap.description.Backend.stepsBackendNames;
import static com.teamdevroute.devroute.roadmap.description.Frontend.stepsFrontendBriefNames;
import static com.teamdevroute.devroute.roadmap.description.Frontend.stepsFrontendNames;
import static com.teamdevroute.devroute.roadmap.description.Mobile.*;
import static com.teamdevroute.devroute.roadmap.enums.DevelopmentField.*;

@Service
public class RoadmapService {
    private final RoadmapStepRepository roadmapStepRepository;


    public RoadmapService(RoadmapStepRepository roadmapStepRepository) {
        this.roadmapStepRepository = roadmapStepRepository;
    }

    public List<RoadmapResponseDTO> findByDevelpmentField(String develpmentField) {
        List<RoadmapStep> roadmapSteps = roadmapStepRepository.findByDevelopmentField(develpmentField)
                .orElseThrow(()->new RuntimeException("Repository " +
                        "hasn't this development roadmapstep"));
        return roadmapSteps.stream().map(roadmapStep -> RoadmapResponseDTO.builder().
                brief_info(roadmapStep.getBrief_info())
                .name(roadmapStep.getName())
                .build()).collect(Collectors.toList());
    }
    public DetailedRoadmapResponseDTO findByDevelpmentFieldAndStepsName(String develpmentField, String stepsName) {
        return new DetailedRoadmapResponseDTO();
    }
    public void updateAllRoadMap(){
        updateBackendRoadMap();
        updateFrontendRoadMap();
        updateAiRoadMap();
        updateIosRoadMap();
        updateAndroidRoadMap();
    }
    public void updateRoadMap(String[] stepNames, String[] stepBriefNames, String developmentField) {
        for (int i = 0; i < stepNames.length; i++) {
            roadmapStepRepository.save(RoadmapStep.builder()
                    .name(stepNames[i])
                    .developmentField(developmentField)
                    .brief_info(stepBriefNames[i])
                    .build());
        }
    }

    public void updateBackendRoadMap() {
        updateRoadMap(stepsBackendNames, stepBackendBriefNames, String.valueOf(BACKEND));
    }

    public void updateAndroidRoadMap() {
        updateRoadMap(stepsAndroidNames, stepsAndroidBriefNames, String.valueOf(MOBILE_ANDROID));
    }

    public void updateIosRoadMap() {
        updateRoadMap(stepsIosNames, stepsIosBriefNames, String.valueOf(MOBILE_IOS));
    }

    public void updateFrontendRoadMap() {
        updateRoadMap(stepsFrontendNames, stepsFrontendBriefNames, String.valueOf(FROTEND));
    }

    public void updateAiRoadMap() {
        updateRoadMap(stepsAiNames, stepsAiBriefNames, String.valueOf(AIANDDATA));
    }
}
