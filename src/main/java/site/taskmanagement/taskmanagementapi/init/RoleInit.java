package site.taskmanagement.taskmanagementapi.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import site.taskmanagement.taskmanagementapi.model.Role;
import site.taskmanagement.taskmanagementapi.repository.RoleRepository;

@Component
@RequiredArgsConstructor
public class RoleInit {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void init(){
        Role admin = new Role();
        admin.setId(1);
        admin.setRoleName("admin");
        roleRepository.save(admin);

        Role user = new Role();
        user.setId(2);
        user.setRoleName("user");
        roleRepository.save(user);
    }
}
