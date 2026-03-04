package site.taskmanagement.taskmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.taskmanagement.taskmanagementapi.dto.UserResponse;
import site.taskmanagement.taskmanagementapi.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
