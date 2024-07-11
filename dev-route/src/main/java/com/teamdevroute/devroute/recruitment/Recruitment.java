package com.teamdevroute.devroute.recruitment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
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

    @Builder
    public Recruitment(List<String> techStacks, String annual, LocalDateTime dueDate, String url) {
        this.techStacks = techStacks;
        this.annual = annual;
        this.dueDate = dueDate;
        this.url = url;
    }
}
