package rtuitlab.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.auth.models.jpa.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByUsername(String username);
}
