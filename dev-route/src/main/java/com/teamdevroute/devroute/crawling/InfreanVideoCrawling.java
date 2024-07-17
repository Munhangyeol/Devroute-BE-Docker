package com.teamdevroute.devroute.crawling;

import static com.teamdevroute.devroute.video.constans.ApiConstans.INFREAN_CRAWRLING_URL_SEARCH;

import com.teamdevroute.devroute.video.dto.infrean.InfreanVideoDTO;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InfreanVideoCrawling {

    public ArrayList<InfreanVideoDTO> crawlingInfreanVideo(TechnologyStackName teck_stack){
        WebDriver driver = getWebDriver(teck_stack);
        ArrayList result = new ArrayList<InfreanVideoDTO>();
        try {
            //WebElement 추출
            List<WebElement> lectures = getLectures(driver);
            // 각 강의 요소를 순회하며 데이터 추출
            for (WebElement lecture : lectures) {
                if (result.size() >= 10) {
                    break;
                }
                try {
//                    System.out.println(lecture.getText());
                    String thumbnailUrl = getUrl(lecture, "div.mantine-AspectRatio-root img", "src", "No image");
                    // 강의 URL 추출
                    String lectureUrl = getUrl(lecture, "a", "href", "No URL");
                    // 강의 제목 추출
                    String title=lecture.getText().split("\n")[0];
                    // 가격 추출
                    String price=lecture.getText().split("\n")[2];
                    // 결과 출력
                    InfreanVideoDTO infreanVideoDTO = new InfreanVideoDTO(lectureUrl,title,thumbnailUrl,Long.valueOf(price.replaceAll("[^\\d]", "")));
                    result.add(infreanVideoDTO);
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    log.info("Element not found in this lecture element: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("An unexpected error occurred: " + e.getMessage(), e);
        } finally {
            // 브라우저 닫기
            driver.quit();
        }
        return result;
    }

    private String getUrl(WebElement lecture, String cssSelector, String src, String x) {
        // 썸네일 이미지 URL 추출
        WebElement thumbnailElement = lecture.findElement(
                By.cssSelector(cssSelector));
        String thumbnailUrl = thumbnailElement != null ? thumbnailElement.getAttribute(src) : x;
        return thumbnailUrl;
    }

    private List<WebElement> getLectures(WebDriver driver) {
        // 페이지가 로드될 때까지 잠시 대기 (필요에 따라 조정)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.css-y21pja li.mantine-1avyp1d")));
        // 강의 목록 요소를 선택
        List<WebElement> lectures = driver.findElements(By.cssSelector("ul.css-y21pja li.mantine-1avyp1d"));
        return lectures;
    }

    private WebDriver getWebDriver(TechnologyStackName teck_stack) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get(INFREAN_CRAWRLING_URL_SEARCH+teck_stack+"&types=ONLINE");
        return driver;
    }

}
