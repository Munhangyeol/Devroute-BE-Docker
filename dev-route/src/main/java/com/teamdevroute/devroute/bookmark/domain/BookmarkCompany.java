package com.teamdevroute.devroute.bookmark.domain;

import com.teamdevroute.devroute.company.domain.Company;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkCompany {
    private Long id;
    private String name;
    private String logoUrl;
    private Double grade;
    private String averageSalary;

    public static BookmarkCompany from(Company company) {
        return BookmarkCompany.builder()
                .id(company.getId())
                .name(company.getName())
                .logoUrl(company.getLogoUrl())
                .grade(company.getGrade())
                .averageSalary(company.getAverageSalary())
                .build();
    }
}
