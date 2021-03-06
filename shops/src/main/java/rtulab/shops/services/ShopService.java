package rtulab.shops.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtulab.shops.models.mongoDocuments.Shop;
import rtulab.shops.repositories.ShopRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {
    private ShopRepository shopRepository;

    public Shop get(String id) {
        return shopRepository.getById(id);
    }

    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    public Shop update(String id, Shop newShop) {
        newShop.setId(id);
        return shopRepository.save(newShop);
    }

    public List<Shop> create(Shop cart) {
        shopRepository.save(cart);
        return shopRepository.findAll();
    }
    
    public List<Shop> delete(String id) {
        shopRepository.deleteById(id);
        return shopRepository.findAll();
    }
}