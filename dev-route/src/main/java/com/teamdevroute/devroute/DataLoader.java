package com.teamdevroute.devroute;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import com.teamdevroute.devroute.user.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import com.teamdevroute.devroute.video.Repository.VideoRepository;
import com.teamdevroute.devroute.video.domain.Videos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Profile("default")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = userRepository.save(
                User.builder()
                        .email("admin@example.com")
                        .name("윤성원")
                        .userRole("USER")
                        .password(encoder.encode("1234"))
                        .developField(DevelopField.BACKEND)
                        .loginType("NORMAL")
                        .build());

        User user2 = userRepository.save(
                User.builder()
                        .email("user1@example.com")
                        .name("성원윤")
                        .userRole("ADMIN")
                        .password(encoder.encode("1234"))
                        .developField(DevelopField.AI)
                        .loginType("NORMAL")
                        .build());

        Company company1 = createCompany(
                "카카오", "2034", "카카오는 이런저런 회사입니다.",
                5L, "https://kakao.com", 3.4
        );

        Company company2 = createCompany(
                "네이버", "5090", "네이버는 제가 가고싶은 회사입니다.",
                2L, "https://naver.com", 2.4
        );

        Company company3 = createCompany(
                "티몬", "-5000", "이 회사는 망한 회사입니다.",
                0L, "https://timon.com", 0.1
        );

        Company company4 = createCompany(
                "삼성", "4000", "이재용이 대빵인 회사입니다.",
                1L, "https://samsung.com", 4.0
        );

        createRecruitment(
                company1, Arrays.asList("JAVA", "SPRING"),
                "4년차", LocalDateTime.now(),
                Source.JUMPIT, "https://kakao.com/backend",
                DevelopField.AI
        );
        createRecruitment(
                company1, Arrays.asList("HTML", "REACT"),
                "2년차", LocalDateTime.now(),
                Source.JUMPIT, "https://kakao.com/frontend",
                DevelopField.DATA_SCIENCE
        );

        createRecruitment(
                company2, Arrays.asList("PHP", "GIT"),
                "경력무관", LocalDateTime.now(),
                Source.SARAMIN, "https://naver.com",
                DevelopField.BACKEND
        );

        createRecruitment(
                company4, Arrays.asList("AWS", "Docker"),
                "10년", LocalDateTime.now(),
                Source.SARAMIN, "https://samsung.com",
                DevelopField.MOBILE
        );

        createVideo(
                10000L, "Spring JPA",
                "https://inflearn.co.kr",
                0L
        );
        createVideo(
                20000L, "React Native",
                "https://udemy.co.kr",
                0L
        );
        createVideo(
                5000L, "AWS",
                "https://youtube.co.kr",
                0L
        );
        createVideo(
                0L, "SQL",
                "https://inflearn.co.kr",
                0L
        );
    }

    private Company createCompany(
            String name, String averageSalary,
            String info, Long recruitCount,
            String logoUrl, Double grade
    ) {
        Company company = Company.builder()
                .name(name)
                .averageSalary(averageSalary)
                .grade(grade)
                .clickCount(0L)
                .info(info)
                .recruitCount(recruitCount)
                .logoUrl(logoUrl)
                .build();
        companyRepository.save(company);
        return company;
    }

    private void createRecruitment(
            Company company,
            List<String> techStacks,
            String annual,
            LocalDateTime dueDate,
            Source source,
            String url,
            DevelopField developField
    ) {
        Recruitment recruitment = Recruitment.builder()
                .company(company)
                .developField(developField)
                .url(url)
                .techStacks(techStacks)
                .annual(annual)
                .dueDate(dueDate)
                .source(source)
                .build();
        recruitmentRepository.save(recruitment);
    }

    private void createVideo(
        Long price, String title,
        String url, Long count
    ) {
        Videos videos = Videos.builder()
                .price(price)
                .title(title)
                .url(url)
                .count(count)
                .build();
        videoRepository.save(videos);
    }
}
