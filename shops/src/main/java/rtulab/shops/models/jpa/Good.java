//package rtulab.shops.models.jpa;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class Good {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "cost")
//    private Integer cost;
//
//    @Column(name = "amount")
//    private Integer amount;
//
//    @ManyToMany
//    private Set<Category> categories;
//}
