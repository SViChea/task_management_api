package site.taskmanagement.taskmanagementapi.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import site.taskmanagement.taskmanagementapi.model.Role;
import site.taskmanagement.taskmanagementapi.model.User;
import site.taskmanagement.taskmanagementapi.repository.RoleRepository;
import site.taskmanagement.taskmanagementapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInitialize {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        List<Role> roleAdmin = new ArrayList<>();
        roleAdmin.add(roleRepository.findById(1));
        User admin =  new User();
        admin.setName("admin");
        admin.setEmail("admin@taskmanagement.site");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setIsEnabled(true);
        admin.setRoles(roleAdmin);
        userRepository.save(admin);

        List<Role> roleUser = new ArrayList<>();
        roleUser.add(roleRepository.findById(2));
        User user =  new User();
        user.setName("user");
        user.setEmail("user@taskmanagement.site");
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setIsEnabled(true);
        user.setRoles(roleUser);
        userRepository.save(user);
    }

}
