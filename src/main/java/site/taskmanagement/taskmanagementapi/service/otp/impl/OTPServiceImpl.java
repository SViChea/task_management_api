package site.taskmanagement.taskmanagementapi.service.otp.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.taskmanagement.taskmanagementapi.dto.OTPRequest;
import site.taskmanagement.taskmanagementapi.model.OTPCode;
import site.taskmanagement.taskmanagementapi.model.User;
import site.taskmanagement.taskmanagementapi.repository.OTPCodeRepository;
import site.taskmanagement.taskmanagementapi.repository.UserRepository;
import site.taskmanagement.taskmanagementapi.service.email.EmailService;
import site.taskmanagement.taskmanagementapi.service.otp.OTPService;

import java.time.LocalDateTime;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {

    private final OTPCodeRepository otpCodeRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public String generateOTP(User user) {
//        Generate 6 OTP Code
        String otp = String.format("%06d", new Random().nextInt(999999));
        OTPCode otpCode = new OTPCode();

        if (otpCodeRepository.existsByUserId(user.getId())) {
            otpCode = otpCodeRepository.findByUser_Email(user.getEmail());
            otpCode.setExpiresAt(LocalDateTime.now().plusMinutes(10));
            otpCode.setOtp(otp);
        } else {
            otpCode = OTPCode.builder()
                    .otp(otp)
                    .user(user)
                    .isUsed(false)
                    .expiresAt(LocalDateTime.now().plusMinutes(10))
                    .build();
        }

        otpCodeRepository.save(otpCode);
        return otp;
    }

    @Override
    public OTPCode validateOTP(String email, String otp) {
        OTPCode otpCode = otpCodeRepository
                .findByOtpAndUser_Email(otp, email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid OTP"));

        if (otpCode.isUsed()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"OTP has already been used");
        }

        if (otpCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"OTP has expired");
        }

        return otpCode;
    }

    @Override
    public void markAsUsed(OTPCode otpCode) {
        otpCode.setUsed(true);
        otpCodeRepository.save(otpCode);
    }
}
