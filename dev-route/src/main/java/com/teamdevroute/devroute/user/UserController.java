package com.teamdevroute.devroute.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @PostMapping("/signup")
    public ResponseEntity createUser() {

    }
}
