package rtuitlab.buys.services;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.buys.models.BoughtGood;
import rtuitlab.buys.models.Category;
import rtuitlab.buys.models.Receipt;
import rtuitlab.buys.repositories.CategoryRepository;
import rtuitlab.buys.repositories.ReceiptRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ReceiptService {
    private ReceiptRepository receiptRepository;
    private CategoryRepository categoryRepository;

    public List<Receipt> getAll() {
        return receiptRepository.findAll();
    }

    public Receipt getById(Long id) {
        return receiptRepository.getById(id);
    }

    public List<Receipt> create(Receipt receipt) {
        for(BoughtGood boughtGood: receipt.getBoughtGoods()) {
            for (Category category: boughtGood.getCategories()) {
                if(!categoryRepository.existsById(category.getId())) {
                    categoryRepository.save(category);
                }
            }
        }
        receiptRepository.save(receipt);
        return receiptRepository.findAll();
    }

    public Receipt update(Long id, Receipt receipt) {
        receipt.setId(id);
        receiptRepository.save(receipt);
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
