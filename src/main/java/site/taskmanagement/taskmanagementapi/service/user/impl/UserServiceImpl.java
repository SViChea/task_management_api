package site.taskmanagement.taskmanagementapi.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import site.taskmanagement.taskmanagementapi.dto.SignUpRequest;
import site.taskmanagement.taskmanagementapi.model.User;
import site.taskmanagement.taskmanagementapi.repository.RoleRepository;
import site.taskmanagement.taskmanagementapi.repository.UserRepository;
import site.taskmanagement.taskmanagementapi.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exist");

        if(userRepository.existsByUsername(signUpRequest.username()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exist");

        User user = new User();
        user.setName(signUpRequest.name());
        user.setEmail(signUpRequest.email());
        user.setUsername(signUpRequest.username());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user.setRoles(List.of(roleRepository.findByRoleName("user")));
        user.setIsEnabled(true);

        userRepository.save(user);
    }
}
