package com.teamdevroute.devroute.recruitment.controller;

import com.teamdevroute.devroute.recruitment.service.RecruitmentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentUpdateService recruitmentUpdateService;

    @GetMapping("/fetch-jobs")
    public String fetchJobs() {
        recruitmentUpdateService.fetchJobs();
        return "Job fetching initiated";
    }

}