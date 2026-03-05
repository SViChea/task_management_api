package site.taskmanagement.taskmanagementapi.dto;

public record SignUpRequest(
        String name,
        String username,
        String email,
        String password
) {
}
