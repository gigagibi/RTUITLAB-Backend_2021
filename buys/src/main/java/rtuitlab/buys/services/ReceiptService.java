package rtuitlab.buys.services;

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
}
