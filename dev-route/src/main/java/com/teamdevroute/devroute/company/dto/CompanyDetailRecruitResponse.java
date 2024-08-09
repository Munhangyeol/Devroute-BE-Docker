package com.teamdevroute.devroute.company.dto;

import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CompanyDetailRecruitResponse {
    private DevelopField developField;
    private List<String> techStacks;
    private LocalDateTime dueDate;

    @Builder
    public CompanyDetailRecruitResponse(DevelopField developField, List<String> techStacks, LocalDateTime dueDate) {
        this.developField = developField;
        this.techStacks = techStacks;
        this.dueDate = dueDate;
    }

    public static CompanyDetailRecruitResponse from(Recruitment recruitment) {
        return CompanyDetailRecruitResponse.builder()
                .developField(recruitment.getDevelopField())
                .techStacks(recruitment.getTechStacks())
                .dueDate(recruitment.getDueDate())
                .build();
    }
}
