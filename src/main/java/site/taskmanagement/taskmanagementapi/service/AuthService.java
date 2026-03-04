package site.taskmanagement.taskmanagementapi.service;

import site.taskmanagement.taskmanagementapi.dto.LoginRequest;
import site.taskmanagement.taskmanagementapi.dto.UserResponse;

public interface AuthService {
    String userLogin (LoginRequest loginRequest);
}
