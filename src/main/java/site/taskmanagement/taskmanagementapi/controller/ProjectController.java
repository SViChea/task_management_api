package site.taskmanagement.taskmanagementapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.taskmanagement.taskmanagementapi.base.BaseResponse;
import site.taskmanagement.taskmanagementapi.dto.project.ProjectRequest;
import site.taskmanagement.taskmanagementapi.service.project.ProjectService;

import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public BaseResponse<Object> getProjectsByUserId(@RequestParam Long userId){
        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Get Projects by User")
                .data(projectService.getProjectsByUserId(userId))
                .build();
    }

    @PostMapping()
    public BaseResponse<Object> createProject(@RequestBody ProjectRequest projectRequest){
        return BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .message("Create Project")
                .data(projectService.createProject(projectRequest))
                .build();
    }

    @PatchMapping()
    public BaseResponse<Object> updateProject(@RequestParam Long projectId, @RequestBody ProjectRequest projectRequest){
        return BaseResponse.builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Update Project")
                .data(projectService.updateProject(projectId, projectRequest))
                .build();
    }

    @DeleteMapping()
    public BaseResponse<Object> deleteProject(@RequestParam Long userId, @RequestParam Long projectId){
        projectService.deleteProject(userId, projectId);
        return BaseResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .timestamp(LocalDateTime.now())
                .message("Delete Project")
                .data("Project has been deleted")
                .build();
    }

}
