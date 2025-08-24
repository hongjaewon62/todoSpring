package com.todo.todoSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)       // 컨트롤러에서 발생한 IllegalArgumentException을 처리
    public ResponseEntity<String> handleNotFound(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)  // 상태를 404 로 설정
                .body(ex.getMessage());
    }
}