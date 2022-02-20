package rtuitlab.buys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rtuitlab.buys.models.BoughtGood;
import rtuitlab.buys.models.Category;

import java.util.List;
import java.util.Set;

public interface BoughtGoodRepository extends JpaRepository<BoughtGood, Long> {
    @Modifying
    @Query(value = "update BoughtGood set name=?2, goodId=?3, cost=?4, amount=?5, categories=?6 where id=?1")
    BoughtGood update(Long id, String name, String goodId, Integer cost, Integer amount, List<Category> categories);
}
