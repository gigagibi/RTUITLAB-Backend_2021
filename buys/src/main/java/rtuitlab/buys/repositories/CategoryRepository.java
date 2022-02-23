package rtuitlab.buys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rtuitlab.buys.models.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category getByName(String name);
}
