package site.taskmanagement.taskmanagementapi.service.project;

import site.taskmanagement.taskmanagementapi.dto.project.ProjectRequest;
import site.taskmanagement.taskmanagementapi.dto.project.ProjectResponse;

import java.util.List;

public interface ProjectService {

    List<ProjectResponse> getProjectsByUserId(Long userId);

    ProjectResponse getProjectByProjectId(Long projectId);

    ProjectResponse createProject(ProjectRequest projectRequest);

    ProjectResponse updateProject(Long projectId, ProjectRequest projectRequest);

    void deleteProject(Long userId, Long projectId);
}
