package rtulab.shops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rtulab.shops.models.mongo.Category;
import rtulab.shops.models.mongo.CategoryRepository;

@SpringBootApplication
public class ShopsApplication {
    @Autowired
    private CategoryRepository categoryRepository;
    public static void main(String[] args) {
        SpringApplication.run(ShopsApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(CategoryRepository categoryRepository) {
        return args -> {
            Category category = new Category();
            category.setId("abc123");
            category.setName("test");
            categoryRepository.insert(category);
            System.out.println(categoryRepository.findAll());
        };
    }
}