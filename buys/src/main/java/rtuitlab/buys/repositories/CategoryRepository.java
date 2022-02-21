package rtuitlab.buys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rtuitlab.buys.models.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category getByName(String name);

    @Modifying
    @Query(value = "update Category set name = ?2 where id = ?1")
    Category update(String id, String categoryName);
}
