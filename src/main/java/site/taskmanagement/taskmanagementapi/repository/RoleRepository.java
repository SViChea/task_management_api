package site.taskmanagement.taskmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.taskmanagement.taskmanagementapi.model.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findById(int id);
    Role findByRoleName(String name);
}
