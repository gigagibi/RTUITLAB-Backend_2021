package rtulab.shops.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rtulab.shops.models.mongoDocuments.Good;
import rtulab.shops.repositories.GoodRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodService {
    private GoodRepository goodRepository;

    public Good get(String id) {
        return goodRepository.getById(id);
    }

    public List<Good> getAll() {
        return goodRepository.findAll();
    }

    public List<Good> create(Good cart) {
        goodRepository.insert(cart);
        return goodRepository.findAll();
    }

    public Good update(String id, Good newGood) {
        return goodRepository.save(newGood);
    }

    public List<Good> delete(String id) {
        goodRepository.deleteById(id);
        return goodRepository.findAll();
    }
}