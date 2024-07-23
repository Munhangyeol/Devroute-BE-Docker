package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import com.teamdevroute.devroute.user.enums.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);
        return ResponseEntity.created(URI.create("/users/" + response.id())).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserLoginRequest loginRequest
    ) {
        String token = userService.login(loginRequest);

        log.info("토큰: " + token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return new ResponseEntity<>("유저가 로그인되었습니다.",headers, HttpStatus.OK);
    }

    @GetMapping("/auth/kakao")
    public ResponseEntity getKakaoRedirectUrl() {
        String url = userService.getRedirectUrl(LoginType.KAKAO);
//        map.put("url", userService.getRedirectUrl(LoginType.KAKAO));
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/auth/kakao/callback")
    public ResponseEntity<String> kakaoCallback(@RequestParam("code") String code) {
        // accesstoken 받기
        String accessToken = userService.getAccessToken(code, LoginType.KAKAO);
        //
        String token = userService.authLogin(accessToken, LoginType.KAKAO);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return new ResponseEntity<>("유저가 로그인되었습니다.",headers, HttpStatus.OK);
    }
}
