package com.example.testproject.controller;

import com.example.testproject.service.UserService;
import com.example.testproject.service.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest.getUserId(),loginRequest.getPassword()));
    }

    @PostMapping(value= "/signup",produces="text/plain;charset=utf-8")
    public ResponseEntity<String> signUp(@RequestBody LoginRequestDto loginRequest){
        userService.signUp(loginRequest.getUserId(), loginRequest.getPassword());
        return ResponseEntity.ok("회원가입 완료");
    }
}