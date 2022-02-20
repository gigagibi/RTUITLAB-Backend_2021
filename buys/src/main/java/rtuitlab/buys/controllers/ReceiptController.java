package rtuitlab.buys.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rtuitlab.buys.models.Receipt;
import rtuitlab.buys.services.ReceiptService;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@AllArgsConstructor
public class ReceiptController {
    private ReceiptService receiptService;

    @GetMapping("/my")
    public List<Receipt> getMyReceipts(@RequestHeader("Authorization") String token) {
        return receiptService.getByToken(token);
    }

    @GetMapping("/my/{receipt_id}")
    public Receipt getReceipt(@RequestHeader("Authorization") String token, @PathVariable(name = "receipt_id") Long receiptId) {
        return receiptService.getByTokenAndId(token, receiptId);
    }

    @PostMapping("/my")
    public List<Receipt> createReceipt(@RequestBody Receipt receipt) {
//        return receiptService.create(new Receipt(0L, receipt.getUsername(), receipt.getPaymentMethod(), receipt.getShopId(), receipt.getBoughtGoods()));
        return receiptService.create(receipt);
    }
}
