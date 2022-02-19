//package rtulab.shops.models.jpa;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Column(name = "username")
//    private String username;
//
//    @ManyToMany
//    private Set<Good> goods;
//}
