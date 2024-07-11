package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);
        return ResponseEntity.created(URI.create("/users/" + response.id())).body(response);
    }

}
