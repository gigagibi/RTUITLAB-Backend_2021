package rtulab.shops.controllers;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import rtulab.shops.models.mongoDocuments.Good;
import rtulab.shops.models.mongoDocuments.Shop;
import rtulab.shops.services.ShopService;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private ShopService shopService;

    @GetMapping("/")
    public List<Shop> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/{shop_id}/goods")
    public List<Good> getGoodsFromShop(@PathVariable(name = "shop_id") String shopId) {
        return shopService.get(shopId).getGoods();
    }

    @GetMapping("/{shop_id}/goods/")
    public Good getGoodFromShop(@PathVariable(name = "shop_id") String shopId, @RequestParam(name = "good_id") String goodId) {
        return shopService.get(shopId).getGoods().stream().filter(good -> good.getId().equals(goodId)).findFirst().orElse(null);
    }



}
