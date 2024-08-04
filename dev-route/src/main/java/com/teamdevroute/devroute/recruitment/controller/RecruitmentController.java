package com.teamdevroute.devroute.recruitment.controller;

import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.service.RecruitmentService;
import com.teamdevroute.devroute.recruitment.service.RecruitmentUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentUpdateService recruitmentUpdateService;
    private final RecruitmentService recruitmentService;

    @GetMapping("/fetch-jobs")
    public String fetchJobs() {
        recruitmentUpdateService.fetchJobs();
        return "Job fetching initiated";
    }

    @GetMapping("/recruit")
    public ResponseEntity searchRecruitmentByField(
            @RequestParam String type
    ) {
        List<Recruitment> response = recruitmentService.findByType(type);
        return ResponseEntity.ok(response);
    }
}