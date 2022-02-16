package rtuitlab.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.auth.models.jpa.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
