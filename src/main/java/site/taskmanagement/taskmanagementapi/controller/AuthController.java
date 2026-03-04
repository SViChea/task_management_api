package site.taskmanagement.taskmanagementapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.taskmanagement.taskmanagementapi.dto.LoginRequest;
import site.taskmanagement.taskmanagementapi.dto.UserResponse;
import site.taskmanagement.taskmanagementapi.service.AuthService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.userLogin(loginRequest);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
