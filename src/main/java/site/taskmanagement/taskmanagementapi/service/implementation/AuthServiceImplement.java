package site.taskmanagement.taskmanagementapi.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.taskmanagement.taskmanagementapi.dto.LoginRequest;
import site.taskmanagement.taskmanagementapi.dto.UserResponse;
import site.taskmanagement.taskmanagementapi.mapper.UserMapper;
import site.taskmanagement.taskmanagementapi.model.User;
import site.taskmanagement.taskmanagementapi.repository.UserRepository;
import site.taskmanagement.taskmanagementapi.service.AuthService;
import site.taskmanagement.taskmanagementapi.service.JWTService;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Override
    public String userLogin(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.username()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()
        ));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user);
        }

        return "fail";
    }
}
