package site.taskmanagement.taskmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.taskmanagement.taskmanagementapi.model.Project;
import site.taskmanagement.taskmanagementapi.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(long id);

    Optional<List<Task>> findByProject(Project project);

    void deleteTasksByProject(Project project);
}
