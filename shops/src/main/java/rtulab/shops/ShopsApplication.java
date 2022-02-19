package rtulab.shops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import rtulab.shops.models.GoodInCart;
import rtulab.shops.models.mongoDocuments.Cart;
import rtulab.shops.models.mongoDocuments.Category;
import rtulab.shops.models.mongoDocuments.Good;
import rtulab.shops.models.mongoDocuments.Shop;
import rtulab.shops.repositories.CartRepository;
import rtulab.shops.repositories.CategoryRepository;
import rtulab.shops.repositories.GoodRepository;
import rtulab.shops.repositories.ShopRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class ShopsApplication {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private GoodRepository goodRepository;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            System.out.println(categoryRepository.findAll().get(0).getName());
            System.out.println(goodRepository.findAll().get(0).getName());
            System.out.println(shopRepository.findAll().get(0).getName());
            System.out.println(cartRepository.findAll().get(0).getUsername());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopsApplication.class, args);
    }
}