package rtuitlab.buys.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.buys.models.BoughtGood;
import rtuitlab.buys.repositories.BoughtGoodRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BoughtGoodService {
    private BoughtGoodRepository boughtGoodRepository;

    public List<BoughtGood> getAll() {
        return boughtGoodRepository.findAll();
    }

    public BoughtGood getById(Long id) {
        return boughtGoodRepository.getById(id);
    }

    public List<BoughtGood> create(BoughtGood boughtGood) {
        boughtGoodRepository.save(boughtGood);
        return boughtGoodRepository.findAll();
    }

    public BoughtGood update(Long id, BoughtGood boughtGood) {
        boughtGoodRepository.update(id, boughtGood.getName(), boughtGood.getGoodId(), boughtGood.getCost(), boughtGood.getAmount(), boughtGood.getCategories());
        return boughtGoodRepository.getById(id);
    }

    public List<BoughtGood> delete(Long id) {
        boughtGoodRepository.deleteById(id);
        return boughtGoodRepository.findAll();
    }
}
