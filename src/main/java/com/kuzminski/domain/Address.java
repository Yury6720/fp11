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
//@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private Integer building;

    @Column
    private Integer corpus;

    @Column
    private Integer flat;


}
