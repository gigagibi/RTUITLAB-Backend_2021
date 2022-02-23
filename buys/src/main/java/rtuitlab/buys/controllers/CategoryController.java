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

    @GetMapping("/admin")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @PostMapping("/admin")
    public List<Category> createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping("/shops_categories")
    public List<Category> updateCategoriesForGood(@RequestHeader("Authorization") String token, @RequestParam(name = "good_id") String goodId) {
        return categoryService.updateCategoriesForGoodFromShop(token, goodId);
    }
}
