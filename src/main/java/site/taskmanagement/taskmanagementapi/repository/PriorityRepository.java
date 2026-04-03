package site.taskmanagement.taskmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.taskmanagement.taskmanagementapi.model.Priority;

import java.util.Optional;

public interface PriorityRepository extends JpaRepository<Priority,Long> {
    Optional<Priority> findById(long id);
}
