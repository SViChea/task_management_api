package site.taskmanagement.taskmanagementapi.dto.Project;

public record ProjectRequest(
        Long userId,
        String title,
        String description
) {
}
