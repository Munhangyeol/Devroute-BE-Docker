package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.crawling.dto.CrawledRecruitmentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CrawlingTest {

    @Value("${crawling.link.jobplanet}")
    private String JOBPLANET_URL;

    @Autowired
    private WebDriverUtil webDriverUtil;

    @Autowired
    private CompanyCrawlingService companyCrawlingService;

    @Test
    void driver_connect() {
        WebDriverUtil webDriverUtil = new WebDriverUtil();
        webDriverUtil.getChromeDriver(JOBPLANET_URL);
    }

    @Test
    void get_thirty_companies() throws InterruptedException {
        CompanyCrawling crawling = new CompanyCrawling(webDriverUtil, companyCrawlingService);
        crawling.getCompanyThreePage();
    }

    @Test
    void get_recruitment() {
        List<String> companyNames = new ArrayList<>(Arrays.asList("삼성"));
        RecruitmentCrawling recruitmentCrawling = new RecruitmentCrawling(webDriverUtil);
        List<CrawledRecruitmentDto> dtoList = recruitmentCrawling.crawlingJUMPIT(companyNames);
        assertThat(dtoList.size()).isEqualTo(10);
    }
}
