package rtulab.shops.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import rtulab.shops.models.mongoDocuments.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {
    Cart getById(String id);
    Cart getByUsernameAndShopId(String username, String shopId);
}
