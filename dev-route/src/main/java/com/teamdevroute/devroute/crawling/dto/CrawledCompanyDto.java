package com.teamdevroute.devroute.crawling.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class CrawledCompanyDto {

    private List<String> enterpriseNames;
    private List<String> enterpriseSalaries;
    private List<String> enterpriseGrades;
    private List<String> enterpriseLogo;

    public CrawledCompanyDto() {

    }

    @Builder
    public CrawledCompanyDto(List<String> enterpriseNames, List<String> enterpriseSalaries, List<String> enterpriseGrades, List<String> enterpriseLogo) {
        this.enterpriseNames = enterpriseNames;
        this.enterpriseSalaries = enterpriseSalaries;
        this.enterpriseGrades = enterpriseGrades;
        this.enterpriseLogo = enterpriseLogo;
    }

    public void mergeCrawledCompanyDto(CrawledCompanyDto into) {
        this.enterpriseNames.addAll(into.enterpriseNames);
        this.enterpriseGrades.addAll(into.enterpriseGrades);
        this.enterpriseLogo.addAll(into.enterpriseLogo);
        this.enterpriseSalaries.addAll(into.enterpriseSalaries);
    }
}
