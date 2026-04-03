package site.taskmanagement.taskmanagementapi.dto.task;

import site.taskmanagement.taskmanagementapi.model.Progress;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        LocalDateTime dueDate,
        String progressName,
        String priorityName
) {
}
