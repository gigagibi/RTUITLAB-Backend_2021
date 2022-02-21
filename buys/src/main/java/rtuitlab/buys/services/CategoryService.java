package rtuitlab.buys.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.buys.models.Category;
import rtuitlab.buys.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(String id) {
        return categoryRepository.getById(id);
    }

    public List<Category> create(Category category) {
        categoryRepository.save(category);
        return categoryRepository.findAll();
    }

    public Category update(String id, Category category) {
        categoryRepository.update(id, category.getName());
        return categoryRepository.getById(id);
    }

    public List<Category> delete(String id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }
}
