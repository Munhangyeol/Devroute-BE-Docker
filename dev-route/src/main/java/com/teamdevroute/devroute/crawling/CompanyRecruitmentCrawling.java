package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.crawling.dto.CrawledCompanyDto;
import com.teamdevroute.devroute.crawling.dto.CrawledRecruitmentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CompanyRecruitmentCrawling {

    private final CompanyCrawling companyCrawling;
    private final RecruitmentCrawling recruitmentCrawling;

    public CompanyRecruitmentCrawling(CompanyCrawling companyCrawling, RecruitmentCrawling recruitmentCrawling) {
        this.companyCrawling = companyCrawling;
        this.recruitmentCrawling = recruitmentCrawling;
    }

    //@Scheduled(fixedRate = 60000)
    public void companyAndRecruitmentCrawling() {
        CrawledCompanyDto crawledCompanyDto = companyCrawling.getCompanyThreePage();
        log.info(crawledCompanyDto.toString());
        recruitmentCrawling.crawlingJUMPIT(crawledCompanyDto.getEnterpriseNames());
    }

}
