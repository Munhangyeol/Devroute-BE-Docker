package com.teamdevroute.devroute.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Component
@Getter
public class WebDriverUtil {

    private WebDriver driver;

    public void getChromeDriver(String url) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");  // gpu 비활성화

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);

        // 10초까지 기다려준다. 10초 안에 웹 화면이 표시되면 바로 다음 작업이 진행됨
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(url);
    }

    public void closeChromeDriver() {
        driver.quit();
    }
}
