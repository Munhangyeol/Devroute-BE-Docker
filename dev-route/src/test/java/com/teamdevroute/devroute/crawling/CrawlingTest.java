package com.teamdevroute.devroute.crawling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

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
        crawling.getThirtyCompany(1);
        crawling.getThirtyCompany(2);
        crawling.getThirtyCompany(3);
    }
}
