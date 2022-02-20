package rtuitlab.buys.services;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.buys.models.Receipt;
import rtuitlab.buys.repositories.ReceiptRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReceiptService {
    private ReceiptRepository receiptRepository;

    public List<Receipt> getAll() {
        return receiptRepository.findAll();
    }

    public Receipt getById(Long id) {
        return receiptRepository.getById(id);
    }

    public List<Receipt> create(Receipt receipt) {
        receiptRepository.saveAndFlush(receipt);
        return receiptRepository.findAll();
    }

    public Receipt update(Long id, Receipt receipt) {
        receiptRepository.update(id, receipt.getPaymentMethod(), receipt.getShopId(), receipt.getBoughtGoods());
        return receiptRepository.getById(id);
    }

    public List<Receipt> delete(Long id) {
        receiptRepository.deleteById(id);
        return receiptRepository.findAll();
    }

    public List<Receipt> getByToken(String token) {
        String username = JWT.decode(token.substring(7)).getSubject();
        return receiptRepository.getAllByUsername(username);
    }

    public Receipt getByTokenAndId(String token, Long id) {
        String username = JWT.decode(token.substring(7)).getSubject();
        return receiptRepository.getByUsernameAndId(username, id);
    }
}
