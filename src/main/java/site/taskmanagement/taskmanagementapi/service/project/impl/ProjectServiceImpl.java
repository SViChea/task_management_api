package site.taskmanagement.taskmanagementapi.service.project.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.taskmanagement.taskmanagementapi.dto.project.ProjectRequest;
import site.taskmanagement.taskmanagementapi.dto.project.ProjectResponse;
import site.taskmanagement.taskmanagementapi.mapper.ProjectMapper;
import site.taskmanagement.taskmanagementapi.model.Project;
import site.taskmanagement.taskmanagementapi.model.User;
import site.taskmanagement.taskmanagementapi.repository.ProjectRepository;
import site.taskmanagement.taskmanagementapi.repository.UserRepository;
import site.taskmanagement.taskmanagementapi.service.project.ProjectService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectResponse> getProjectsByUserId(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        List<Project> projects = projectRepository.findProjectsByCreatedByAndIsDeletedFalse(user).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found")
        );

        return projectMapper.toProjectResponseList(projects);
    }

    @Override
    public ProjectResponse getProjectByProjectId(Long projectId) {
        Project project = projectRepository.findProjectByIdAndIsDeletedFalse(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found")
        );

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        User user = userRepository.findById(projectRequest.userId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        Project project = new Project();
        project.setTitle(projectRequest.title());
        project.setDescription(projectRequest.description());
        project.setCreatedDate(LocalDate.now());
        project.setCreatedBy(user);

        projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long projectId, ProjectRequest projectRequest) {
        User user = userRepository.findById(projectRequest.userId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        Project project = projectRepository.findProjectByIdAndIsDeletedFalse(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found")
        );

        if (!project.getCreatedBy().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to update project");
        }

        project.setTitle(projectRequest.title());
        project.setDescription(projectRequest.description());

        projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void deleteProject(Long userId, Long projectId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        Project project = projectRepository.findProjectByIdAndIsDeletedFalse(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found")
        );

        if (!project.getCreatedBy().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to delete project");
        }

        project.setIsDeleted(true);
        projectRepository.save(project);
    }
}
