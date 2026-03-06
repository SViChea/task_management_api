package site.taskmanagement.taskmanagementapi.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import site.taskmanagement.taskmanagementapi.base.BaseResponse;
import site.taskmanagement.taskmanagementapi.dto.LoginRequest;
import site.taskmanagement.taskmanagementapi.dto.OTPRequest;
import site.taskmanagement.taskmanagementapi.dto.SignUpRequest;
import site.taskmanagement.taskmanagementapi.dto.UserResponse;
import site.taskmanagement.taskmanagementapi.service.AuthService;
import site.taskmanagement.taskmanagementapi.service.otp.OTPService;
import site.taskmanagement.taskmanagementapi.service.user.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public BaseResponse<Object> login(@RequestBody LoginRequest loginRequest) {
        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .message("User Login")
                .timestamp(LocalDateTime.now())
                .data(authService.userLogin(loginRequest))
                .build();

    }

    @PostMapping("/signup")
    public BaseResponse<Object> signUp(@RequestBody SignUpRequest signUpRequest) throws MessagingException {
        userService.signUp(signUpRequest);
        return BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Create User Account")
                .timestamp(LocalDateTime.now())
                .data("Successfully created user account")
                .build();
    }

    @PostMapping("/verify-otp")
    public BaseResponse<Object> verifyOtp(@RequestBody OTPRequest request) {
        userService.verifyOtp(request);
        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Verify OTP")
                .timestamp(LocalDateTime.now())
                .data("OTP Verified successfully")
                .build();
    }

    @PostMapping("/resend-otp")
    public BaseResponse<Object> resendOtp(@RequestParam String email)
            throws MessagingException {

        userService.resendOtp(email);
        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Re-send OTP")
                .timestamp(LocalDateTime.now())
                .data("OTP sent successfully")
                .build();
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
