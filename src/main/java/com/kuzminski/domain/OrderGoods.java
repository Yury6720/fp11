//package com.kuzminski.domain;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "s_order_goods")
//@RequiredArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
//public class OrderGoods {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "order_id")
//    private Long orderId;
//
//    @Column(name = "good_id")
//    private Long goodId;
//
//    @Column(name = "count")
//    private Integer count;
//
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", insertable = false, updatable = false)
//    private Order order;
//
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "good_id", insertable = false, updatable = false)
//    private Good good;
//}
