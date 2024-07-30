package com.teamdevroute.devroute.roadmap.dto;

import lombok.Builder;

import java.util.List;

public record DetailedRoadmapResponseDTO(String description,
                                         List<String>  related_tecks,
                                         List<String> releated_enterprise
                                                 , int frequencyUse ) {
    @Builder
    public DetailedRoadmapResponseDTO(String description,List<String>  related_tecks,
                                      List<String> releated_enterprise,int frequencyUse){
        this.description = description;
        this.related_tecks = related_tecks;
        this.releated_enterprise = releated_enterprise;
        this.frequencyUse=frequencyUse;
    }

}
