package rtulab.shops.controllers.api;

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

    @GetMapping("/")
    public List<Good> getGoods() {
        return goodService.getAll();
    }

    @GetMapping("/{good_id}")
    public Good getGood(@PathVariable(name = "good_id") String goodId) {
        return goodService.get(goodId);
    }
}
