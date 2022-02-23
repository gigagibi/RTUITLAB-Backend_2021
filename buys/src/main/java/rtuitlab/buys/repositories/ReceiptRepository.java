package rtuitlab.buys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rtuitlab.buys.models.BoughtGood;
import rtuitlab.buys.models.Receipt;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> getAllByUsername(String username);

    Receipt getByUsernameAndId(String username, Long id);

}
