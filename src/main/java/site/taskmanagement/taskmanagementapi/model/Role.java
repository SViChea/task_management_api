package site.taskmanagement.taskmanagementapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    private int id;

    @Column(nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> user;

    @Override
    public @Nullable String getAuthority() {
        return "ROLE_" + this.roleName;
    }
}
