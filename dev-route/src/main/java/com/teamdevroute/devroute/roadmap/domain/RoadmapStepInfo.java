package com.teamdevroute.devroute.roadmap.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamdevroute.devroute.company.Company;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Setter
@Getter
public class RoadmapStepInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roadmap_step_info_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="roadmap_step_id")
    private RoadmapStep roadmapStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
    private String description;
    @Column(name = "technology_stacks", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> technology_stack;
    @Column( columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> companies;
    private int used_ratio;

    public RoadmapStepInfo() {
    }
    @Builder
    public RoadmapStepInfo(RoadmapStep roadmapStep,Company company,String description,List<String> technology_stack
    ,List<String> companies,int used_ratio) {
        this.roadmapStep = roadmapStep;
        this.company = company;
        this.description = description;
        this.technology_stack = technology_stack;
        this.companies = companies;
        this.used_ratio = used_ratio;

    }
}
