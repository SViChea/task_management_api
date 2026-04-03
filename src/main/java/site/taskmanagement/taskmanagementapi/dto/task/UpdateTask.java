package site.taskmanagement.taskmanagementapi.dto.task;

import java.time.LocalDateTime;

public record UpdateTask(
        Long id,
        String title,
        String description,
        LocalDateTime dueDate,
        Integer progressId,
        Integer priorityId
) {
}
