package com.teamdevroute.devroute.company.dto;

import com.teamdevroute.devroute.company.domain.Company;
import lombok.Builder;
import lombok.Data;

@Data
public class CompanyResponse {
    private String logoUrl;
    private String name;
    private Long recruitmentCount;
    private Double grade;
    private String salary;

    @Builder
    public CompanyResponse(String logoUrl, String name, Long recruitmentCount, Double grade, String salary) {
        this.logoUrl = logoUrl;
        this.name = name;
        this.recruitmentCount = recruitmentCount;
        this.grade = grade;
        this.salary = salary;
    }

    public static CompanyResponse of(Company company) {
        return CompanyResponse.builder()
                .logoUrl(company.getLogoUrl())
                .name(company.getName())
                .recruitmentCount(company.getRecruitCount())
                .grade(company.getGrade())
                .salary(company.getAverageSalary())
                .build();
    }
}
