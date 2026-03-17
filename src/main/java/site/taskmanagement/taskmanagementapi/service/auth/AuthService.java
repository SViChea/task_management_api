package site.taskmanagement.taskmanagementapi.service.auth;

import site.taskmanagement.taskmanagementapi.dto.LoginRequest;

public interface AuthService {
    String userLogin (LoginRequest loginRequest);
}
