package rtulab.shops.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtulab.shops.models.mongoDocuments.Good;
import rtulab.shops.repositories.GoodRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        newGood.setId(id);
        return goodRepository.save(newGood);
    }

    public List<Good> delete(String id) {
        goodRepository.deleteById(id);
        return goodRepository.findAll();
    }

    public Good getByIdFromShop(String shopId, String goodId) {
        return goodRepository.findGoodByShopIdAndId(shopId, goodId);
    }

    public List<Good> getAllFromShop(String shopId) {
        return goodRepository.findAllByShopId(shopId);
    }

    public List<Good> getAllFromShopByCategories(String shopId, String categoryId) {
        return goodRepository.findAllByShopId(shopId).stream().filter(g -> g.getCategoriesIds().contains(categoryId)).collect(Collectors.toList());
    }
}