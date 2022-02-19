package rtulab.shops.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtulab.shops.models.mongoDocuments.Shop;

public interface ShopRepository extends MongoRepository<Shop, String> {
    Shop getById(String id);
}
