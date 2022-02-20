package rtulab.shops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import rtulab.shops.models.mongoDocuments.Category;
import rtulab.shops.models.mongoDocuments.Good;
import rtulab.shops.models.mongoDocuments.Shop;
import rtulab.shops.repositories.CartRepository;
import rtulab.shops.repositories.CategoryRepository;
import rtulab.shops.repositories.GoodRepository;
import rtulab.shops.repositories.ShopRepository;

import java.util.ArrayList;

@SpringBootApplication
@EnableEurekaClient
public class ShopsApplication {
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Autowired
//    private ShopRepository shopRepository;
//    @Autowired
//    private CartRepository cartRepository;
//    @Autowired
//    private GoodRepository goodRepository;
//
//    @Bean
//    CommandLineRunner runner() {
//        return args -> {
//            Good good = new Good("1", "test good", 100, 10, new ArrayList<>(), null);
//            good.getCategoriesIds().add("1");
//            Shop shop = new Shop("1", "test shop", "test address", "test phone", new ArrayList<>());
//            good.setShopId(shop.getId());
//            shop.getGoodsIds().add(good.getId());
//
//            categoryRepository.insert(new Category("1", "test category"));
//            goodRepository.insert(good);
//            shopRepository.insert(shop);
//        };
//    }
    public static void main(String[] args) {
        SpringApplication.run(ShopsApplication.class, args);
    }
}