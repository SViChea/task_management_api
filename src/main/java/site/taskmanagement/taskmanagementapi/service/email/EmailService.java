package site.taskmanagement.taskmanagementapi.service.email;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendOtpEmail(String to, String name, String otp) throws MessagingException;
}
