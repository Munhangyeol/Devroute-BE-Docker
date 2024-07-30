package com.teamdevroute.devroute.crawling.dto;

import com.teamdevroute.devroute.company.Company;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.Source;
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


//    List<String> techStacks, String annual, LocalDateTime dueDate, String url, Company company, Source source
    public Recruitment toEntity(Company company, Source source) {
        return Recruitment.builder()
                .techStacks(techList)
                .annual(career)
                .company(company)
                .source(source)
                .build();
    }
}
