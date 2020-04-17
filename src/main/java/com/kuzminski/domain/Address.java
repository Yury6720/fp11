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

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String building;

    @Column
    private Integer corpus;

    @Column
    private String flat;


}
