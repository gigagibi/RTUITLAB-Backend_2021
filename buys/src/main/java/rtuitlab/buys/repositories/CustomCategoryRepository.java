package rtuitlab.buys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.buys.models.CustomCategory;

import java.util.List;

public interface CustomCategoryRepository extends JpaRepository<CustomCategory, Long> {
    List<CustomCategory> findAllByUsername(String username);
    CustomCategory findByUsernameAndId(String username, Long id);
    void deleteByUsernameAndId(String username, Long id);
}
