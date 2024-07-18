package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.global.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleNotFoundUserException(UserNotFoundException e) {
        return ResponseEntity.badRequest().body("유저를 찾을 수 없습니다.");
    }
}
