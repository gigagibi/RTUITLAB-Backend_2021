package rtulab.shops.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rtulab.shops.models.mongoDocuments.Shop;
import rtulab.shops.services.ShopService;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
@AllArgsConstructor
public class  ShopController {
    private ShopService shopService;

    @GetMapping("/")
    public List<Shop> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/{shop_id}")
    public Shop getShopInfo(@PathVariable(name = "shop_id") String shopId) {
        return shopService.get(shopId);
    }

    @PostMapping("/admin/")
    public List<Shop> createShop(@RequestBody Shop shop) {
        return shopService.create(shop);
    }

    @DeleteMapping("/admin/{shop_id}")
    public List<Shop> deleteShop(@PathVariable(name = "shop_id") String shopId) {
        return shopService.delete(shopId);
    }

    @PutMapping("/admin/{shop_id}")
    public Shop updateShop(@PathVariable(name = "shop_id") String shopId, @RequestBody Shop shop) {
        return shopService.update(shopId, shop);
    }
}
