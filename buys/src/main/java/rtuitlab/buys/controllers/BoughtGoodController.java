package rtuitlab.buys.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.buys.services.BoughtGoodService;

@RestController
@RequestMapping("/api/goods")
@AllArgsConstructor
public class BoughtGoodController {
    private BoughtGoodService boughtGoodService;
}
