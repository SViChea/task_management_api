package site.taskmanagement.taskmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.taskmanagement.taskmanagementapi.model.Project;
import site.taskmanagement.taskmanagementapi.model.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByIdAndIsDeletedFalse(Long projectId);
    Optional<List<Project>> findProjectsByCreatedByAndIsDeletedFalse(User user);
}
