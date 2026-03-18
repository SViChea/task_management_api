package site.taskmanagement.taskmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.taskmanagement.taskmanagementapi.model.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
