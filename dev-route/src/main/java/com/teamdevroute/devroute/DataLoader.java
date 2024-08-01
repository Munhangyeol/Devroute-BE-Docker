package com.teamdevroute.devroute;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.user.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("default")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private CompanyRepository companyRepository;

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

        createCompany(
                "카카오", "2034", "카카오는 이런저런 회사입니다.",
                5L, "https://kakao.com", 3.4
        );

        createCompany(
                "네이버", "5090", "네이버는 제가 가고싶은 회사입니다.",
                2L, "https://naver.com", 2.4
        );

        createCompany(
                "티몬", "-5000", "이 회사는 망한 회사입니다.",
                0L, "https://timon.com", 0.1
        );

        createCompany(
                "삼성", "4000", "이재용이 대빵인 회사입니다.",
                1L, "https://samsung.com", 4.0
        );
    }

    private void createCompany(
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
    }
}
