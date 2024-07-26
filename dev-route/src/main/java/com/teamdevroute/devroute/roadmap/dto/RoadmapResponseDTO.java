package com.teamdevroute.devroute.roadmap.dto;

import lombok.Builder;

public record RoadmapResponseDTO(String name, String brief_info) {

    @Builder
    public RoadmapResponseDTO(String name,String brief_info){
        this.name=name;
        this.brief_info = brief_info;
    }
}
