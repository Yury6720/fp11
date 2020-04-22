package com.kuzminski.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "s_address")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private Integer building;

    @Column(name = "corpus")
    private Integer corpus;

    @Column(name = "flat")
    private Integer flat;


}
