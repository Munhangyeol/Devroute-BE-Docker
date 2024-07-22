package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.global.auth.Oauth2Util;
import com.teamdevroute.devroute.global.auth.ELoginProvider;
import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.global.auth.jwt.JwtUtils;
import com.teamdevroute.devroute.global.exception.UserNotFoundException;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import com.teamdevroute.devroute.user.enums.LoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;
    private final Oauth2Util oauth2Util;

    public UserCreateResponse createUser(UserCreateRequest request) {
        User user = userRepository.save(request.toEntity(LoginType.NORMAL.name()));
        return UserCreateResponse.of(user);
    }

    public String login(UserLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        if(!encoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        LoginUserInfo loginUserInfo = LoginUserInfo.builder()
                .id(user.getId())
                .role(user.getUserRole())
                .name(user.getName())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .build();

        return jwtUtils.create(loginUserInfo);
    }

    public String getAccessToken(String authorizationCode, ELoginProvider ELoginProvider){
        String accessToken = null;
        switch (ELoginProvider) {
            case KAKAO -> {
                accessToken = oauth2Util.getKakaoAccessToken(authorizationCode);
            }
//            case GOOGLE -> {
//                accessToken = oauth2Util.getGoogleAccessToken(authorizationCode);
//            }
//            case NAVER -> {
//                accessToken = authorizationCode;
//            }
        }
        return accessToken;
    }

}
