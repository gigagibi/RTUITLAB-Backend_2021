package rtulab.shops.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rtulab.shops.models.mongoDocuments.Good;
import rtulab.shops.services.GoodService;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
@AllArgsConstructor
public class GoodController {
    private GoodService goodService;

    @GetMapping("/admin")
    public List<Good> getGoods() {
        return goodService.getAll();
    }

    @GetMapping("/admin/{good_id}")
    public Good getGood(@PathVariable(name = "good_id") String goodId) {
        return goodService.get(goodId);
    }

    @GetMapping("/shop/{shop_id}/good/{good_id}")
    public Good getGoodFromShop(@PathVariable(name = "shop_id") String shopId, @PathVariable(name = "good_id") String goodId) {
        return goodService.getByIdFromShop(shopId, goodId);
    }

    @GetMapping("/shop/{shop_id}")
    public List<Good> getGoodsFromShop(@PathVariable(name = "shop_id") String shopId) {
        return goodService.getAllFromShop(shopId);
    }

    @PostMapping("/admin")
    public List<Good> createGood(@RequestBody Good good) {
        return goodService.create(good);
    }

    @DeleteMapping("/admin/{good_id}")
    public List<Good> deleteGood(@PathVariable(name = "good_id") String goodId) {
        return goodService.delete(goodId);
    }

    @PutMapping("/admin/{good_id}")
    public Good updateGood(@PathVariable(name = "good_id") String goodId, @RequestBody Good good) {
        return goodService.update(goodId, good);
    }
}
