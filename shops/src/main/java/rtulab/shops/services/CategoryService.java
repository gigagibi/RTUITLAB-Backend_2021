package rtulab.shops.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtulab.shops.models.mongoDocuments.Category;
import rtulab.shops.repositories.CategoryRepository;
import rtulab.shops.repositories.GoodRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private GoodRepository goodRepository;

    public Category get(String id) {
        return categoryRepository.getById(id);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> create(Category cart) {
        categoryRepository.insert(cart);
        return categoryRepository.findAll();
    }

    public Category update(String id, Category newCategory) {
        newCategory.setId(id);
        return categoryRepository.save(newCategory);
    }

    public List<Category> delete(String id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }

    public List<Category> getCategoriesFromGood(String goodId) {
        return categoryRepository.findAllByIdIn(goodRepository.getById(goodId).getCategoriesIds());
    }
}
