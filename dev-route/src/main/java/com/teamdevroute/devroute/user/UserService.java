package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreateResponse createUser(UserCreateRequest request) {
        User user = userRepository.save(request.toEntity());
        return UserCreateResponse.of(user);
    }
}
