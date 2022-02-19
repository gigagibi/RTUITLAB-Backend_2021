package rtulab.shops.models.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Good {
    @Id
    private String id;
    private String name;
    private Integer cost;
    private Integer amount;

    @DBRef
    private Set<Category> categories;
}
