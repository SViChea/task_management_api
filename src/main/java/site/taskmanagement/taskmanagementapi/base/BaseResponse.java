package site.taskmanagement.taskmanagementapi.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseResponse<T>(
        Integer status,
        LocalDateTime timestamp,
        String message,
        T data
) {
}
