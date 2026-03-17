package site.taskmanagement.taskmanagementapi.mapper;

import org.mapstruct.Mapper;
import site.taskmanagement.taskmanagementapi.dto.Project.ProjectResponse;
import site.taskmanagement.taskmanagementapi.model.Project;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectResponse toProjectResponse(Project project);
    List<ProjectResponse> toProjectResponseList(List<Project> projects);
}
