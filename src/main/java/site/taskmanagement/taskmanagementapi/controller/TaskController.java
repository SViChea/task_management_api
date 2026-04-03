package site.taskmanagement.taskmanagementapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.taskmanagement.taskmanagementapi.base.BaseResponse;
import site.taskmanagement.taskmanagementapi.dto.task.TaskRequest;
import site.taskmanagement.taskmanagementapi.dto.task.UpdateTask;
import site.taskmanagement.taskmanagementapi.service.task.TaskService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public BaseResponse<Object> getTaskById(long id){
        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Get Task By Id")
                .data(taskService.getTaskById(id))
                .build();
    }

    @GetMapping("/projects")
    public BaseResponse<Object> getTasksByProjectId(long projectId){
        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Get Tasks By Project")
                .data(taskService.getTaskByProjectId(projectId))
                .build();
    }

    @PostMapping()
    public BaseResponse<Object> createTask(@RequestBody TaskRequest taskRequest){
        return BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .message("Create Task")
                .data(taskService.createTask(taskRequest))
                .build();
    }

    @PatchMapping()
    public BaseResponse<Object> updateTask(@RequestBody UpdateTask updateTask){
        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Update Task")
                .data(taskService.updateTask(updateTask))
                .build();
    }

    @DeleteMapping()
    public BaseResponse<Object> deleteTask(long id){
        taskService.deleteTaskById(id);
        return BaseResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .timestamp(LocalDateTime.now())
                .message("Delete Task")
                .data("Task Delete Successfully")
                .build();
    }
}
