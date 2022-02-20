package rtuitlab.buys.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class BoughtGood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "good_id")
    private String goodId;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "amount")
    private Integer amount;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Category> categories;
}
