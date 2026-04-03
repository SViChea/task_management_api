package site.taskmanagement.taskmanagementapi.dto.task;

import java.time.LocalDateTime;

public record TaskRequest(
        String title,
        String description,
        LocalDateTime dueDate,
        Integer progressId,
        Integer priorityId,
        Long projectId
) {
}
