package rtulab.shops.models.mongoDocuments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Shop {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private List<Good> goods;
}
