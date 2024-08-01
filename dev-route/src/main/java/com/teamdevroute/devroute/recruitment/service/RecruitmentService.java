package com.teamdevroute.devroute.recruitment.service;

import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class RecruitmentService {

    private RecruitmentRepository recruitmentRepository;

}
