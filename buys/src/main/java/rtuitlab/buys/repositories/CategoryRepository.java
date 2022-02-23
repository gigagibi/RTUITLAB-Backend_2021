package rtuitlab.buys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rtuitlab.buys.models.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category getByName(String name);

}
