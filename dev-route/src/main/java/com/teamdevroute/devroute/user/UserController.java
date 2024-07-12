package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.global.auth.AuthorizationProvider;
import com.teamdevroute.devroute.global.auth.UserAuthContext;
import com.teamdevroute.devroute.global.auth.UserCredential;
import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Controller
public class UserController {
    private final UserService userService;
    private final AuthorizationProvider authorizationProvider;

    public UserController(UserService userService, AuthorizationProvider authorizationProvider) {
        this.userService = userService;
        this.authorizationProvider = authorizationProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);
        return ResponseEntity.created(URI.create("/users/" + response.id())).body(response);
    }

    @PostMapping("/login")
    public void login(
            @RequestBody UserLoginRequest loginRequest,
            HttpServletResponse response
    ) {
        UserAuthContext userAuthContext = userService.loginByEmailAndPassword(loginRequest);
        UserCredential userCredential = authorizationProvider.create(userAuthContext);
        Cookie cookie = new Cookie("token", userCredential.authorization());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
