package site.taskmanagement.taskmanagementapi.service.otp;

import jakarta.mail.MessagingException;
import site.taskmanagement.taskmanagementapi.dto.OTPRequest;
import site.taskmanagement.taskmanagementapi.model.OTPCode;
import site.taskmanagement.taskmanagementapi.model.User;

public interface OTPService {
    public String generateOTP(User user);

    public OTPCode validateOTP(String email, String otp);

    public void markAsUsed(OTPCode otpCode);
}
