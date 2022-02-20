package rtulab.shops.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtulab.shops.models.mongoDocuments.Good;

import java.util.List;

public interface GoodRepository  extends MongoRepository<Good, String> {
    Good getById(String id);
    List<Good> getAllByShopId(String shopId);
    Good getGoodByShopIdAndId(String shopId, String goodId);
}
