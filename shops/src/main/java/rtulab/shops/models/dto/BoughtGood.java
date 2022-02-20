package rtulab.shops.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rtulab.shops.models.mongoDocuments.Category;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BoughtGood {
    private String name;
    private String goodId;
    private Integer cost;
    private Integer amount;
    private List<Category> categories;
}
