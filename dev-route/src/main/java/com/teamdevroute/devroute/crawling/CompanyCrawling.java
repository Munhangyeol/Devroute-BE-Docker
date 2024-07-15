package com.teamdevroute.devroute.crawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CompanyCrawling {

    private static String JOBPLANET_URL = "https://www.jobplanet.co.kr/companies?industry_id=700&_rs_act=industries&_rs_con=gnb&_rs_element=category";
    private static String JOBPLANET_PAGE_2_URL = "https://www.jobplanet.co.kr/companies?industry_id=700&page=2";
    private static String JOBPLANET_PAGE_3_URL = "https://www.jobplanet.co.kr/companies?industry_id=700&page=3";

    private WebDriverUtil webDriverUtil;

    public CompanyCrawling(WebDriverUtil webDriverUtil) {
        this.webDriverUtil = webDriverUtil;
    }

    public void getThirtyCompany(int page) throws InterruptedException {
        String URL = switch (page) {
            case 2 -> JOBPLANET_PAGE_2_URL;
            case 3 -> JOBPLANET_PAGE_3_URL;
            default -> JOBPLANET_URL;
        };

        webDriverUtil.getChromeDriver(URL);
        WebDriver driver = webDriverUtil.getDriver();

        // 정보를 담을 JSON
        JSONObject info = new JSONObject();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 현재 페이지의 소스코드 가져오기
        Document doc = Jsoup.parse(driver.getPageSource());

        // 상위 10개 기업 이름 및 연봉 가져오기
        List<String> enterpriseNames = new ArrayList<>();
        List<String> enterpriseSalaries = new ArrayList<>();
        List<String> enterpriseGrades = new ArrayList<>();

        // 팝업 제거를 위한 뒤로 갔다 앞으로 오기
        driver.navigate().back();
        driver.navigate().forward();


        try{
            // 상위 10개 기업 이름 받아서 List에 저장
            for(WebElement element : driver.findElements(By.className("us_titb_l3"))){

                String data = element.getText();

                if(data.isBlank()){
                    continue;
                }

                String subData = data.substring(0, data.length() - 9);
                enterpriseNames.add(subData);


            }
            for(WebElement element : driver.findElements(By.className("notranslate"))){
                String data = element.getText();
                enterpriseSalaries.add(data);
            }
            for(WebElement element : driver.findElements(By.className("gfvalue"))) {
                String data = element.getText();
                enterpriseGrades.add(data);
            }

            // 연봉 리스트의 0, 1은 더미 데이터라 삭제
            enterpriseSalaries.remove(1);
            enterpriseSalaries.remove(0);


        } catch(Exception e){
            e.printStackTrace();
        }

        // 상위 10개 기업 연봉 및 이름 보기
        for(int i = 0; i<10;i++){
            enterpriseNames.get(i);
            System.out.print(" 연봉: " + enterpriseSalaries.get(i));
        }

        webDriverUtil.closeChromeDriver();
    }
}
