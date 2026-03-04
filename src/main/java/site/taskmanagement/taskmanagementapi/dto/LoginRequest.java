package site.taskmanagement.taskmanagementapi.dto;

public record LoginRequest(
        String username,
        String password
) {
}
