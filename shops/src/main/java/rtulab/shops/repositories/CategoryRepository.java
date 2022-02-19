package rtulab.shops.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtulab.shops.models.mongoDocuments.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category getById(String id);
}
