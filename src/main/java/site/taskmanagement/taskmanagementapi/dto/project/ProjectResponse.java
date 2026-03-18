package site.taskmanagement.taskmanagementapi.dto.project;

import java.util.Date;

public record ProjectResponse(
        Long id,
        String title,
        String description,
        Date createdDate
) {
}
