package com.kuzminski.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "s_order")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "date")
  private Timestamp date;

  @Column(name = "is_active")
  private boolean isActive;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  @JsonIdentityReference(alwaysAsId = true)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "delivery")
  private Address address;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "s_order_goods",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "good_id"))
  private Set<Goods> orderGoods = Collections.emptySet();

  @Column(name = "count")
  private int count;
}
