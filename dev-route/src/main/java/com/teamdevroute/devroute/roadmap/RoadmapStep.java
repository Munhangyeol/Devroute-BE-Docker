package com.teamdevroute.devroute.roadmap;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class RoadmapStep {
    @Id
    @GeneratedValue
    @Column(name = "roadmap_step_id")
    private Long id;
    private String development_field;
    private String name;
    private String brief_info;


}
