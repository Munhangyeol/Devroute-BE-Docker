package com.teamdevroute.devroute.crawling;

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
    private TechnologyStackName teck_stck;
    public ArrayList<InfreanVideoDTO> crawlingInfreanVideo(TechnologyStackName teck_stck){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ArrayList result = new ArrayList<InfreanVideoDTO>();
        WebDriver driver = new ChromeDriver(options);
        try {
            // 인프런 강의 목록 페이지로 이동
            driver.get("https://www.inflearn.com/courses/it-programming/web-dev?skill="+teck_stck+"&types=ONLINE");
            // 페이지가 로드될 때까지 잠시 대기 (필요에 따라 조정)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.css-y21pja li.mantine-1avyp1d")));
            // 강의 목록 요소를 선택
            List<WebElement> lectures = driver.findElements(By.cssSelector("ul.css-y21pja li.mantine-1avyp1d"));
            int tryNumber = 0;

            // 각 강의 요소를 순회하며 데이터 추출
            for (WebElement lecture : lectures) {

                tryNumber += 1;
                if(tryNumber>10)
                    break;
                try {
//                    System.out.println(lecture.getText());
                    // 썸네일 이미지 URL 추출
                    WebElement thumbnailElement = lecture.findElement(
                            By.cssSelector("div.mantine-AspectRatio-root img"));
                    String thumbnailUrl = thumbnailElement != null ? thumbnailElement.getAttribute("src") : "No image";

                    // 강의 URL 추출
                    WebElement linkElement = lecture.findElement(By.cssSelector("a"));
                    String lectureUrl = linkElement != null ? linkElement.getAttribute("href") : "No URL";

                    // 강의 제목 추출
                    String title=lecture.getText().split("\n")[0];

                    // 가격 추출
                    String price=lecture.getText().split("\n")[2];
                    // 결과 출력
                    InfreanVideoDTO infreanVideoDTO = new InfreanVideoDTO(lectureUrl,title,thumbnailUrl,Long.valueOf(price.replaceAll("[^\\d]", "")));
                    System.out.println("Thumbnail URL: " + thumbnailUrl);
                    System.out.println("Lecture URL: " + lectureUrl);
                    System.out.println("Title: " + title);
                    System.out.println("Price: "+price);
                    result.add(infreanVideoDTO);
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.out.println("Element not found in this lecture element: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 브라우저 닫기
            driver.quit();
        }
        return result;
    }

}
