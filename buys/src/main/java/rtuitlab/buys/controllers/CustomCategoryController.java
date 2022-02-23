package rtuitlab.buys.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rtuitlab.buys.models.CustomCategory;
import rtuitlab.buys.services.CustomCategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/my_categories")
public class CustomCategoryController {
    private CustomCategoryService customCategoryService;
    
    // admin methods
    
    @GetMapping("/admin")
    public List<CustomCategory> getCustomCategories() {
        return customCategoryService.getAll();
    }
    
    @GetMapping("/admin/{id}")
    public CustomCategory getCustomCategory(@PathVariable Long id) {
        return customCategoryService.getById(id);
    }
    
    @PostMapping("admin/")
    public List<CustomCategory> createCustomCategory(@RequestBody CustomCategory customCategory) {
        return customCategoryService.create(customCategory);
    }
    
    @PutMapping("/admin/{id}")
    public CustomCategory updateCustomCategory(@PathVariable Long id, @RequestBody CustomCategory customCategory) {
        return customCategoryService.update(id, customCategory);
    }
    
    @DeleteMapping("/admin/{id}")
    public List<CustomCategory> deleteCustomCategory(@PathVariable Long id) {
        return customCategoryService.delete(id);
    }

    // user methods

    @GetMapping("/")
    public List<CustomCategory> getMyCustomCategories(@RequestHeader("Authorization") String token) {
        return customCategoryService.getAllMyCustomCategories(token);
    }

    @GetMapping("/{id}")
    public CustomCategory getMyCustomCategory(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return customCategoryService.getMyCustomCategory(token, id);
    }

    @PostMapping("/")
    public List<CustomCategory> createMyCustomCategory(@RequestHeader("Authorization") String token, @RequestBody CustomCategory customCategory) {
        return customCategoryService.createMyCustomCategory(token, customCategory);
    }

    @PutMapping("/{id}")
    public CustomCategory updateMyCustomCategory(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody CustomCategory customCategory) {
        return customCategoryService.updateMyCustomCategory(token, id, customCategory);
    }

    @DeleteMapping("/{id}")
    public List<CustomCategory> deleteMyCustomCategory(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return customCategoryService.deleteMyCustomCategory(token, id);
    }
    
}
