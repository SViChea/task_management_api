package site.taskmanagement.taskmanagementapi.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import site.taskmanagement.taskmanagementapi.model.OTPCode;

import java.util.Optional;

public interface OTPCodeRepository extends JpaRepository<OTPCode, Long> {
    Optional<OTPCode> findByOtpAndUser_Email(String otp, String email);

    OTPCode findByUser_Email(String email);

    Boolean existsByUserId(Long id);

    void deleteOTPCodesByUserId(Long id);
}
