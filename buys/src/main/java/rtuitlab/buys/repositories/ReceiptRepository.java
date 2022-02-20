package rtuitlab.buys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rtuitlab.buys.models.BoughtGood;
import rtuitlab.buys.models.Receipt;

import java.util.Set;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @Modifying
    @Query(value = "update Receipt set paymentMethod=?2, shopId=?3, boughtGoods=?4 where id=?1")
    Receipt update(Long id, String paymentMethod, String shopId, Set<BoughtGood> boughtGoods);
}
