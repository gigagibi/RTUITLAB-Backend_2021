package rtulab.shops.models.dto;

import java.util.List;

public class Receipt {
    private List<BoughtGood> boughtGoods;
    private String shopId;
    private String paymentMethod;
}
