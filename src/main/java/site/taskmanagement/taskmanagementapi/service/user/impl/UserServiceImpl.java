package site.taskmanagement.taskmanagementapi.service.user.impl;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.taskmanagement.taskmanagementapi.dto.OTPRequest;
import site.taskmanagement.taskmanagementapi.dto.SignUpRequest;
import site.taskmanagement.taskmanagementapi.model.OTPCode;
import site.taskmanagement.taskmanagementapi.model.User;
import site.taskmanagement.taskmanagementapi.repository.RoleRepository;
import site.taskmanagement.taskmanagementapi.repository.UserRepository;
import site.taskmanagement.taskmanagementapi.service.email.EmailService;
import site.taskmanagement.taskmanagementapi.service.otp.OTPService;
import site.taskmanagement.taskmanagementapi.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final OTPService otpService;
    private final EmailService emailService;

    @Override
    public void signUp(SignUpRequest signUpRequest) throws MessagingException {
        if(userRepository.existsByEmail(signUpRequest.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exist");

        if(userRepository.existsByUsername(signUpRequest.username()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exist");

        User user = new User();
        user.setName(signUpRequest.name());
        user.setEmail(signUpRequest.email());
        user.setUsername(signUpRequest.username());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user.setRoles(List.of(roleRepository.findByRoleName("user")));
        user.setIsEnabled(true);

        userRepository.save(user);

        String otp = otpService.generateOTP(user);
        emailService.sendOtpEmail(user.getEmail(), user.getName(), otp);
    }

    @Transactional
    @Override
    public void resendOtp(String email) throws MessagingException {

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getIsVerified()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account is already verified");
        }

        String otp = otpService.generateOTP(user);
        emailService.sendOtpEmail(user.getEmail(), user.getName(), otp);
    }

    @Override
    public void verifyOtp(OTPRequest request) {

        // 1. Validate OTP
        OTPCode otpCode= otpService.validateOTP(
                request.email(),
                request.otp()
        );

        // 2. Activate the user account
        User user = otpCode.getUser();
        user.setIsVerified(true);
        userRepository.save(user);

        // 3. Mark OTP as used
        otpService.markAsUsed(otpCode);
    }
}
