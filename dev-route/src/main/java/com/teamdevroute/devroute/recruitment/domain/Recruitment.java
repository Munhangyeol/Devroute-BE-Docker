package com.teamdevroute.devroute.recruitment.domain;

import com.teamdevroute.devroute.company.Company;
import com.teamdevroute.devroute.recruitment.enums.DevelopmentField;
import com.teamdevroute.devroute.recruitment.enums.Source;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "technology_stacks", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> techStacks;

    private String annual;

    private LocalDateTime dueDate;

    private String url;

    @Enumerated(EnumType.STRING)
    private DevelopmentField developmentField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private Source source;

    @Builder
    public Recruitment(List<String> techStacks, String annual, LocalDateTime dueDate, String url, Company company, Source source) {
        this.techStacks = techStacks;
        this.annual = annual;
        this.dueDate = dueDate;
        this.url = url;
        this.company = company;
        this.source = source;
    }
}
