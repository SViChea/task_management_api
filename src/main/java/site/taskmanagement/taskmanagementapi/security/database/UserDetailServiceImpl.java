package site.taskmanagement.taskmanagementapi.security.database;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.taskmanagement.taskmanagementapi.model.User;
import site.taskmanagement.taskmanagementapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);
        return customUserDetails;
    }
}
