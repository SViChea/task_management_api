package site.taskmanagement.taskmanagementapi.dto.project;

public record ProjectRequest(
        Long userId,
        String title,
        String description
) {
}
