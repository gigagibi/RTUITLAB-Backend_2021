package rtuitlab.buys.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rtuitlab.buys.services.ReceiptService;

@RestController
@RequestMapping("/api/receipts")
@AllArgsConstructor
public class ReceiptController {
    private ReceiptService receiptService;
}
