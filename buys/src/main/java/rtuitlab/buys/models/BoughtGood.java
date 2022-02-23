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
public class BoughtGood {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bg_seq_gen")
    @SequenceGenerator(name="bg_seq_gen", sequenceName="bough_good_sequence")
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

    @ManyToMany
    private List<Category> categories;

    @ManyToMany
    private List<CustomCategory> customCategories;
}
