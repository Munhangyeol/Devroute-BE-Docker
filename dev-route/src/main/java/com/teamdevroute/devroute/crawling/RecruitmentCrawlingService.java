package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.company.Company;
import com.teamdevroute.devroute.company.CompanyRepository;
import com.teamdevroute.devroute.crawling.dto.CrawledRecruitmentDto;
import com.teamdevroute.devroute.global.exception.CompanyNotFoundException;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecruitmentCrawlingService {

    private RecruitmentRepository recruitmentRepository;
    private CompanyRepository companyRepository;

    // TODO 채용공고 엔티티 수정 후 구현
    public void createRecruitment(List<CrawledRecruitmentDto> crawledRecruitmentDtoList) {
        for(CrawledRecruitmentDto dto : crawledRecruitmentDtoList) {
            Company company = companyRepository.findByName(dto.getCompanyName())
                            .orElseThrow(CompanyNotFoundException::new);
            Source source = Source.JUMPIT;
            recruitmentRepository.save(dto.toEntity(company, source));
        }
    }
}
