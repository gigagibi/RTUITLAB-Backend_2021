package rtuitlab.buys.services;

import org.springframework.stereotype.Service;
import rtuitlab.buys.models.CustomCategory;
import rtuitlab.buys.repositories.CustomCategoryRepository;

import java.util.List;

@Service
public class CustomCategoryService {
    private CustomCategoryRepository customCategoryRepository;

    public List<CustomCategory> getAll() {
        return customCategoryRepository.findAll();
    }

    public CustomCategory getById(Long id) {
        return customCategoryRepository.getById(id);
    }

    public List<CustomCategory> create(CustomCategory customCategory) {
        customCategoryRepository.save(customCategory);
        return customCategoryRepository.findAll();
    }

    public CustomCategory update(Long id, CustomCategory customCategory) {
        customCategory.setId(id);
        customCategoryRepository.save(customCategory);
        return customCategoryRepository.getById(id);
    }

    public List<CustomCategory> delete(Long id) {
        customCategoryRepository.deleteById(id);
        return customCategoryRepository.findAll();
    }
}
