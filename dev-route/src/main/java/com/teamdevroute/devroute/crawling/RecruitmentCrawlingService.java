package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.crawling.dto.CrawledRecruitmentDto;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecruitmentCrawlingService {

    private RecruitmentRepository recruitmentRepository;

    // TODO 채용공고 엔티티 수정 후 구현
    public void createRecruitment(List<CrawledRecruitmentDto> crawledRecruitmentDtoList) {


    }
}
