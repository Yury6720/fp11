package com.kuzminski.domain;

import lombok.*;

import javax.persistence.*;

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


}
