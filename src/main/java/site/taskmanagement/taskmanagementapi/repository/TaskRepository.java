package site.taskmanagement.taskmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.taskmanagement.taskmanagementapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
