package site.taskmanagement.taskmanagementapi.service.email.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import site.taskmanagement.taskmanagementapi.service.email.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendOtpEmail(String to, String name, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject("Your Verification Code");
        helper.setText(buildOtpEmailTemplate(name, otp), true);  // true = HTML

        mailSender.send(message);
    }

    private String buildOtpEmailTemplate(String name, String otp) {
        return """
            <div style="font-family: Arial, sans-serif; max-width: 500px; margin: auto;">
                <h2 style="color: #333;">Email Verification</h2>
                <p>Hi <strong>%s</strong>,</p>
                <p>Use the code below to verify your account.
                   It expires in <strong>10 minutes</strong>.</p>

                <div style="
                    font-size: 36px;
                    font-weight: bold;
                    letter-spacing: 12px;
                    background: #f4f4f4;
                    padding: 20px;
                    text-align: center;
                    border-radius: 8px;
                    margin: 20px 0;
                ">%s</div>

                <p style="color: #999; font-size: 12px;">
                    If you didn't request this, ignore this email.
                </p>
            </div>
        """.formatted(name, otp);
    }
}
