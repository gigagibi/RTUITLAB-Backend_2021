package rtuitlab.buys.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rtuitlab.buys.models.BoughtGood;
import rtuitlab.buys.services.BoughtGoodService;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
@AllArgsConstructor
public class BoughtGoodController {
    private BoughtGoodService boughtGoodService;

    @GetMapping("/admin")
    public List<BoughtGood> getGoods() {
        return boughtGoodService.getAll();
    }

    @PutMapping("/admin/{good_id}")
    public BoughtGood updateGood(@PathVariable(name = "good_id") Long goodId, @RequestBody BoughtGood good) {
        boughtGoodService.update(goodId, good);
        return boughtGoodService.getById(goodId);
    }

}
