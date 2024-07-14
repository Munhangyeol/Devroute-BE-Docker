package com.teamdevroute.devroute.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrawlingTest {

    @Value("${crawling.link.jobplanet}")
    private String JOBPLANET_URL;

    @Test
    void driver_connect() {
        WebDriverUtil webDriverUtil = new WebDriverUtil();
        webDriverUtil.getChromeDriver(JOBPLANET_URL);
    }

    @Test
    void get_thirty_companies() throws InterruptedException {
        CompanyCrawling crawling = new CompanyCrawling(new WebDriverUtil());
        crawling.getThirtyCompany(1);
        crawling.getThirtyCompany(2);
        crawling.getThirtyCompany(3);
    }
}
