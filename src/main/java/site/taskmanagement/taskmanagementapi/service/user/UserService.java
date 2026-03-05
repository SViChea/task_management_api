package site.taskmanagement.taskmanagementapi.service.user;

import site.taskmanagement.taskmanagementapi.dto.SignUpRequest;

public interface UserService {
    public void signUp(SignUpRequest signUpRequest);
}
