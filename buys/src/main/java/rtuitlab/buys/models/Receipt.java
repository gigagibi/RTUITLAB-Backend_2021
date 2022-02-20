package rtuitlab.buys.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
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



    @OneToMany(cascade = CascadeType.MERGE)
    private Set<BoughtGood> boughtGoods;

}
