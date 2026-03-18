package site.taskmanagement.taskmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.taskmanagement.taskmanagementapi.model.Priority;

public interface PriorityRepository extends JpaRepository<Priority,Long> {
}
