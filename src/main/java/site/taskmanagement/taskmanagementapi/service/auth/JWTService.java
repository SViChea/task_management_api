package site.taskmanagement.taskmanagementapi.service.auth;

import site.taskmanagement.taskmanagementapi.model.User;

public interface JWTService {
    public String generateToken(User user);
}
