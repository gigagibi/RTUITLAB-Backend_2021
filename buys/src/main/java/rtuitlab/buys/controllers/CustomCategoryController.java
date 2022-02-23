package rtuitlab.buys.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.buys.services.CustomCategoryService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/my_categories")
public class CustomCategoryController {
    private CustomCategoryService customCategoryService;
}
