package com.teamdevroute.devroute.crawling.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CrawledRecruitmentDto {
    private String title;
    private String imageUrl;
    private String companyName;
    private List<String> techList;
    private String area;
    private String career;

    @Builder
    public CrawledRecruitmentDto(String title, String imageUrl, String companyName, List<String> techList, String area, String career) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.companyName = companyName;
        this.techList = techList;
        this.area = area;
        this.career = career;
    }
}
