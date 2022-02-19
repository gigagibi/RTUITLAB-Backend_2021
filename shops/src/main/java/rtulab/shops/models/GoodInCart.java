package rtulab.shops.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtulab.shops.models.mongoDocuments.Good;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodInCart {
    private Good good;
    private int boughtAmount;
}
