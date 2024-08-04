package com.teamdevroute.devroute.recruitment.service;

import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class RecruitmentService {

    private RecruitmentRepository recruitmentRepository;

    public List<Recruitment> findByType(String type) {
        DevelopField developField = DevelopField.toEnum(type);
        log.info("RecruitmentService - findByType(): type=" + developField);
        if(developField.equals(DevelopField.NONE)){
            throw new IllegalArgumentException("개발직군에 이상이 있습니다. 확인해주세요.");
        }
        return recruitmentRepository.findByDevelopField(developField);
    }
}
