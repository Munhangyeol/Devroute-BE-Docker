package com.teamdevroute.devroute;

import com.teamdevroute.devroute.user.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
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

    @Override
    public void run(String... args) throws Exception {
        User user1 = userRepository.save(
                User.builder()
                        .email("admin@example.com")
                        .name("윤성원")
                        .userRole("USER")
                        .password(encoder.encode("1234"))
                        .developField("백엔드")
                        .loginType("NORMAL")
                        .build());

        User user2 = userRepository.save(
                User.builder()
                        .email("user1@example.com")
                        .name("성원윤")
                        .userRole("ADMIN")
                        .password(encoder.encode("1234"))
                        .developField("AI")
                        .loginType("NORMAL")
                        .build());
    }
}
