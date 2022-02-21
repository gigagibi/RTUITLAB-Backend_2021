package rtulab.shops.models.dto.buysService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodInCart {
    private String goodId;
    private int boughtAmount;
}
