package rtulab.shops.models.dto.buysService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Receipt {
    private String username;
    private String paymentMethod;
    private String shopId;
    private List<BoughtGood> boughtGoods;
}
