package site.taskmanagement.taskmanagementapi.service.user;

import jakarta.mail.MessagingException;
import site.taskmanagement.taskmanagementapi.dto.OTPRequest;
import site.taskmanagement.taskmanagementapi.dto.SignUpRequest;

public interface UserService {
    public void signUp(SignUpRequest signUpRequest) throws MessagingException;

    public void resendOtp(String email) throws MessagingException;

    public void verifyOtp(OTPRequest request);
}
