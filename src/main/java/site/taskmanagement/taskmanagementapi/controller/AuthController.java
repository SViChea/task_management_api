package site.taskmanagement.taskmanagementapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.taskmanagement.taskmanagementapi.base.BaseResponse;
import site.taskmanagement.taskmanagementapi.dto.LoginRequest;
import site.taskmanagement.taskmanagementapi.dto.SignUpRequest;
import site.taskmanagement.taskmanagementapi.dto.UserResponse;
import site.taskmanagement.taskmanagementapi.service.AuthService;
import site.taskmanagement.taskmanagementapi.service.user.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.userLogin(loginRequest);
    }

    @PostMapping("/signup")
    public BaseResponse<Object> signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        return BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Create User Account")
                .timestamp(LocalDateTime.now())
                .data("Successfully created user account")
                .build();
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
