package rtuitlab.buys.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "shop_id")
    private String shopId;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE}
    )
    private List<BoughtGood> boughtGoods;

}
