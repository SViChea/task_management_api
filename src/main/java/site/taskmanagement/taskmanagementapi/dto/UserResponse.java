package site.taskmanagement.taskmanagementapi.dto;

public record UserResponse(
        String name,
        String username,
        String email,
        Boolean isEnabled
) {
}
