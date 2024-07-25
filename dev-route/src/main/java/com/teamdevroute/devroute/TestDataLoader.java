package com.teamdevroute.devroute;

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
    }
}
