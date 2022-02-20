package rtulab.shops.models.mongoDocuments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rtulab.shops.models.dto.GoodInCart;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    private String id;
    private String username;
    private List<GoodInCart> boughtGoods;
}