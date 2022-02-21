package rtulab.shops.models.dto.buysService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rtulab.shops.models.mongoDocuments.Category;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BoughtGood {
    private String name;
    private String goodId;
    private Integer cost;
    private Integer amount;
    @JsonProperty("categories")
    private List<Category> categories;
}
