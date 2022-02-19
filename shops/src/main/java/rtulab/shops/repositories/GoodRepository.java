package rtulab.shops.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtulab.shops.models.mongoDocuments.Good;

public interface GoodRepository  extends MongoRepository<Good, String> {
    Good getById(String id);
}
