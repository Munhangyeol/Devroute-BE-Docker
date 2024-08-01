package com.teamdevroute.devroute;

import com.teamdevroute.devroute.company.controller.CompanyController;
import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.user.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import com.teamdevroute.devroute.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        final User user1 = userRepository.save(User.builder()
                .name("윤성원")
                .email("admin@email.com")
                .password(encoder.encode("password"))
                .developField(DevelopField.BACKEND)
                .userRole(UserRole.ADMIN.name())
                .goal_info("목표정보")
                .introduce_info("소개말")
                .build());
        final User user2 = userRepository.save(User.builder()
                .name("누군가")
                .email("user@email.com")
                .password(encoder.encode("password"))
                .developField(DevelopField.AI)
                .userRole(UserRole.USER.name())
                .goal_info("목표정보")
                .introduce_info("소개말")
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
