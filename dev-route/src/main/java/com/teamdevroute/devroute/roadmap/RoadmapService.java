package com.teamdevroute.devroute.roadmap;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.roadmap.domain.RoadmapStep;
import com.teamdevroute.devroute.roadmap.domain.RoadmapStepInfo;
import com.teamdevroute.devroute.roadmap.dto.DetailedRoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.dto.RoadmapResponseDTO;
import com.teamdevroute.devroute.roadmap.repository.RoadmapStepInfoRepository;
import com.teamdevroute.devroute.roadmap.repository.RoadmapStepRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static com.teamdevroute.devroute.roadmap.description.Ai.*;
import static com.teamdevroute.devroute.roadmap.description.Backend.*;
import static com.teamdevroute.devroute.roadmap.description.Frontend.*;
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

    public List<RoadmapResponseDTO> findByDevelpmentField(String developmentField) {
        List<RoadmapStep> roadmapSteps = roadmapStepRepository.findByDevelopmentField(developmentField)
                .orElseThrow(()->new RuntimeException("해당 개발 분야에 대한 로드맵 단계가 없습니다: " + developmentField));
        if(roadmapSteps.isEmpty()){
            throw new RuntimeException(new RuntimeException("해당 개발 분야에 대한 로드맵 단계가 없습니다: " + developmentField));
        }
        return roadmapSteps.stream().map(roadmapStep -> RoadmapResponseDTO.builder().
                brief_info(roadmapStep.getBrief_info())
                .name(roadmapStep.getName())
                .build()).collect(Collectors.toList());
    }
    public DetailedRoadmapResponseDTO findByDevelpmentFieldAndStepsName(String develpmentField, String stepsName) {
        RoadmapStep roadmapStep=roadmapStepRepository.findByNameAndDevelopmentField(stepsName, develpmentField).orElseThrow(
                ()->new RuntimeException("해당 이름과 개발 분야에 대한 로드맵 단계가 없습니다")
        );

        RoadmapStepInfo roadmapStepInfo = roadmapStepInfoRepository.findByRoadmapStep(roadmapStep)
                .orElseThrow(  ()->new RuntimeException("해당 로드맵 단계에 대한 정보가 없습니다")
                );

        return  DetailedRoadmapResponseDTO.builder()
                .description(roadmapStepInfo.getDescription())
                .frequencyUse(roadmapStepInfo.getUsed_ratio())
                .related_tecks(roadmapStepInfo.getTechnology_stack())
                .releated_enterprise(roadmapStepInfo.getCompanies())
                .build();
    }
    public void updateAllRoadmaps() {
        if(roadmapStepRepository.count()==0) {
            updateRoadMap(stepsBackendNames, stepBackendBriefNames, stepsBackendDetailedDescription, String.valueOf(BACKEND), null, null, null, 10);
            updateRoadMap(stepsFrontendNames, stepsFrontendBriefNames, stepsFrontendDetailedDescrption, String.valueOf(FRONTEND), null, null, null, 10);
            updateRoadMap(stepsAiNames, stepsAiBriefNames, stepsAiDetailedDescription, String.valueOf(AIANDDATA), null, null, null, 10);
            updateRoadMap(stepsIosNames, stepsIosBriefNames, stepsIosDetailedDescription, String.valueOf(MOBILE_IOS), null, null, null, 10);
            updateRoadMap(stepsAndroidNames, stepsAndroidBriefNames, stepsAndroidDetailedDescription, String.valueOf(MOBILE_ANDROID), null, null, null, 10);
        }
    }


    private void updateRoadMap(String[] stepNames, String[] stepBriefNames,String[] descriptions, String developmentField,
                              Company company,List<String> companies,List<String> teck_stacks,int used_ratio
    ) {
        for (int i = 0; i < stepNames.length; i++) {
            RoadmapStep roadmapstep=roadmapStepRepository.save(RoadmapStep.builder()
                    .name(stepNames[i])
                    .developmentField(developmentField)
                    .brief_info(stepBriefNames[i])
                    .build());
            roadmapStepInfoRepository.save(RoadmapStepInfo.builder().roadmapStep(roadmapstep)
                    .company(company)
                    .companies(companies)
                    .technology_stack(teck_stacks)
                    .used_ratio(used_ratio)
                    .description(descriptions[i])
                    .build());
        }

    }

    public RoadmapStep findById(Long id) {
        return roadmapStepRepository.findById(id)
                .orElseThrow(RoadmapStepNotFoundException::new);
    }
}
