package rtuitlab.buys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rtuitlab.buys.models.BoughtGood;
import rtuitlab.buys.models.Category;

import java.util.List;
import java.util.Set;

public interface BoughtGoodRepository extends JpaRepository<BoughtGood, Long> {
    List<BoughtGood> getAllByGoodId(String goodId);
}
