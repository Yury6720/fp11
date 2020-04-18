package com.kuzminski.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "s_roles")
// @RequiredArgsConstructor
//@Builder
@Getter
@Setter
//@EqualsAndHashCode
@ToString
public class Roles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "role_name")
  private String roleName;

  //    @JsonBackReference
  //    @OneToOne
  //    @JoinColumn(name = "user_id")
  //    private User user;
  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;
}
