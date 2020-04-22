package com.kuzminski.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "s_goods")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Double size;

    @Column
    private String color;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", insertable = false, updatable = false)
//@OneToMany(cascade = CascadeType.ALL,
//        fetch = FetchType.LAZY,
//        mappedBy = "goods")
//    private Set<OrderGoods> orderGoods;
//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "goods")
//    private Set<OrderGoods> orderGoods = Collections.emptySet();

}
