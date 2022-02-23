package rtuitlab.buys.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rtuitlab.buys.models.Category;
import rtuitlab.buys.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    // admin methods

    @GetMapping("/admin")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @PostMapping("/admin")
    public List<Category> createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @DeleteMapping("/admin/{category_id}")
    public List<Category> deleteCategory (@PathVariable(name = "category_id") String categoryId) {
        return categoryService.delete(categoryId);
    }

    @PutMapping("/admin/{category_id}")
    public Category updateCategory (@PathVariable(name = "category_id") String categoryId, @RequestBody Category category) {
        return categoryService.update(categoryId, category);
    }

    // user methods

    @GetMapping("/shops_categories")
    public List<Category> updateCategoriesForGood(@RequestHeader("Authorization") String token, @RequestParam(name = "good_id") String goodId) {
        return categoryService.updateCategoriesForGoodFromShop(token, goodId);
    }
}
