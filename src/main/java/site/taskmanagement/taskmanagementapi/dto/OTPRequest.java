package site.taskmanagement.taskmanagementapi.dto;

public record OTPRequest(
        String email,
        String otp
) {
}
