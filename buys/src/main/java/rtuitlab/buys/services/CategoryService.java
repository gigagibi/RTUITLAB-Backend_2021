package rtuitlab.buys.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.buys.models.Category;
import rtuitlab.buys.repositories.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }

    public List<Category> create(Category category) {
        categoryRepository.saveAndFlush(category);
        return categoryRepository.findAll();
    }

    public Category update(Long id, Category category) {
        categoryRepository.update(id, category.getName());
        return categoryRepository.getById(id);
    }

    public List<Category> delete(Long id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }
}
