package com.teamdevroute.devroute.bookmark;

import com.teamdevroute.devroute.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "bookmark")
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "recruits", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> recruits;

    @Column(name = "companies", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> companies;

    @Column(name = "videos", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> videos;

    @Column(name = "tech_stacks", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> techStacks;

    @Column(name = "roadmaps", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> roadmaps;

    @Builder
    public Bookmark(List<String> recruits, List<String> companies, List<String> videos, List<String> techStacks, List<String> roadmaps) {
        this.recruits = recruits;
        this.companies = companies;
        this.videos = videos;
        this.techStacks = techStacks;
        this.roadmaps = roadmaps;
    }
}
