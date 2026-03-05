package site.taskmanagement.taskmanagementapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import site.taskmanagement.taskmanagementapi.base.BaseResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ServiceException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        BaseResponse<?> errorResponse = new BaseResponse<>(
                e.getStatusCode().value(),
                LocalDateTime.now(),
                e.getReason(),
                e.getReason()
        );

        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }
}