package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.global.auth.UserAuthContext;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import com.teamdevroute.devroute.user.enums.LoginType;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreateResponse createUser(UserCreateRequest request) {
        User user = userRepository.save(request.toEntity(LoginType.NORMAL.name()));
        return UserCreateResponse.of(user);
    }

    public UserAuthContext loginByEmailAndPassword(UserLoginRequest loginRequest) {
        User user = userRepository.findByEmailAndPassword(loginRequest.email(), loginRequest.password())
                .orElseThrow(() -> new IllegalArgumentException("로그인 정보가 올바르지 않습니다."));
        return new UserAuthContext(user.getName(), user.getUserRole());
    }
}
