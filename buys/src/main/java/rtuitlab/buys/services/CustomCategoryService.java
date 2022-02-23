package rtuitlab.buys.services;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.buys.models.CustomCategory;
import rtuitlab.buys.repositories.CustomCategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
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
    // with token
    public List<CustomCategory> getAllMyCustomCategories(String token) {
        String username = JWT.decode(token.substring(7)).getSubject();
        return customCategoryRepository.findAllByUsername(username);
    }

    public CustomCategory getMyCustomCategory(String token, Long id) {
        String username = JWT.decode(token.substring(7)).getSubject();
        return customCategoryRepository.findByUsernameAndId(username, id);
    }

    public List<CustomCategory> createMyCustomCategory(String token, CustomCategory customCategory) {
        String username = JWT.decode(token.substring(7)).getSubject();
        customCategory.setUsername(username);
        customCategoryRepository.save(customCategory);
        return customCategoryRepository.findAll();
    }

    public CustomCategory updateMyCustomCategory(String token, Long id, CustomCategory customCategory) {
        String username = JWT.decode(token.substring(7)).getSubject();
        customCategory.setId(id);
        customCategory.setUsername(username);
        customCategoryRepository.save(customCategory);
        return customCategoryRepository.getById(id);
    }

    public List<CustomCategory> deleteMyCustomCategory(String token, Long id) {
        String username = JWT.decode(token.substring(7)).getSubject();
        customCategoryRepository.deleteByUsernameAndId(username, id);
        return customCategoryRepository.findAll();
    }
}
