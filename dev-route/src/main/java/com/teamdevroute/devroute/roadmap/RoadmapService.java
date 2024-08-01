package com.teamdevroute.devroute.roadmap;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.roadmap.domain.RoadmapStep;
import com.teamdevroute.devroute.roadmap.domain.RoadmapStepInfo;
import com.teamdevroute.devroute.roadmap.dto.DetailedRoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.dto.RoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.repository.RoadmapStepInfoRepository;
import com.teamdevroute.devroute.roadmap.repository.RoadmapStepRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final RoadmapStepInfoRepository roadmapStepInfoRepository;


    public RoadmapService(RoadmapStepRepository roadmapStepRepository, RoadmapStepInfoRepository roadmapStepInfoRepository) {
        this.roadmapStepRepository = roadmapStepRepository;
        this.roadmapStepInfoRepository = roadmapStepInfoRepository;
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
        RoadmapStep roadmapStep=roadmapStepRepository.findByNameAndDevelopmentField(stepsName, develpmentField).orElseThrow(
                ()->new RuntimeException("This roadmap is not exist in this repo")
        );
        RoadmapStepInfo roadmapStepInfo = roadmapStepInfoRepository.findByRoadmapStep(roadmapStep)
                .orElseThrow(  ()->new RuntimeException("This roadmap is not exist in this repo")
                );

        return  DetailedRoadmapResponseDTO.builder()
                .description(roadmapStepInfo.getDescription())
                .frequencyUse(roadmapStepInfo.getUsed_ratio())
                .related_tecks(roadmapStepInfo.getTechnology_stack())
                .releated_enterprise(roadmapStepInfo.getCompanies())
                .build();
    }
    public void updateAllRoadMap(){
        updateBackendRoadMap();
        updateFrontendRoadMap();
        updateAiRoadMap();
        updateIosRoadMap();
        updateAndroidRoadMap();
    }

    //아래에서 null값을 넣어둔 이유는 아직 연관 기술 스택, 연관 기업, 비율 관련해서 미정이기 때문임.
    public void updateBackendRoadMap() {
        updateRoadMap(stepsBackendNames, stepBackendBriefNames, String.valueOf(BACKEND),null,null,null,10);
    }
    public void updateAndroidRoadMap() {
        updateRoadMap(stepsAndroidNames, stepsAndroidBriefNames, String.valueOf(MOBILE_ANDROID),null,null,null,10);
    }

    public void updateIosRoadMap() {
        updateRoadMap(stepsIosNames, stepsIosBriefNames, String.valueOf(MOBILE_IOS),null,null,null,10);
    }

    public void updateFrontendRoadMap() {
        updateRoadMap(stepsFrontendNames, stepsFrontendBriefNames, String.valueOf(FROTEND),null,null,null,10);
    }

    public void updateAiRoadMap() {
        updateRoadMap(stepsAiNames, stepsAiBriefNames, String.valueOf(AIANDDATA),null,null,null,10);
    }
    public void updateRoadMap(String[] stepNames, String[] stepBriefNames, String developmentField,
                              Company company,List<String > companies,List<String> teck_stacks,int used_ratio) {
        for (int i = 0; i < stepNames.length; i++) {
            roadmapStepRepository.save(RoadmapStep.builder()
                    .name(stepNames[i])
                    .developmentField(developmentField)
                    .brief_info(stepBriefNames[i])
                    .build());
            RoadmapStep roadmapstep = roadmapStepRepository.findByNameAndDevelopmentField(stepNames[i], developmentField).orElseThrow(
                    () -> new RuntimeException("This roadmap is not exist in repository")
            );
            roadmapStepInfoRepository.save(RoadmapStepInfo.builder().roadmapStep(roadmapstep)
                    .company(company)
                    .companies(companies)
                    .technology_stack(teck_stacks)
                    .used_ratio(used_ratio)
                    .build());
        }

    }

}
