package rtulab.shops.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rtulab.shops.models.mongoDocuments.Category;
import rtulab.shops.services.CategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("/")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{category_id}")
    public Category getCategory(@PathVariable(name = "category_id") String categoryId) {
        return categoryService.get(categoryId);
    }

    @PostMapping("/admin")
    public List<Category> createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @DeleteMapping("/admin/{category_id}")
    public List<Category> deleteCategory(@PathVariable(name = "category_id") String categoryId) {
        return categoryService.delete(categoryId);
    }

    @PutMapping("/admin/{category_id}")
    public Category createCategory(@PathVariable(name = "category_id") String categoryId, @RequestBody Category category) {
        return categoryService.update(categoryId, category);
    }
}
