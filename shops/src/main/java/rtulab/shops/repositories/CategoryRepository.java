package rtulab.shops.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rtulab.shops.models.mongoDocuments.Category;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category getById(String id);
}
