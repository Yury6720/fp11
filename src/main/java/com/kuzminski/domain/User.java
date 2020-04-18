package com.kuzminski.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "s_users")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "is_active")
  private boolean isActive;

  @Column(name = "creation_date")
  private Timestamp creationDate;

  @Column(name = "email")
  private String email;

  @JsonIgnore
  @Column(name = "phone")
  private String phone;

  //  @OneToOne(fetch = FetchType.EAGER)
  //  @JoinColumn(name = "role")
  //  private Roles roles;

  //  @JsonManagedReference
  //  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
  //  private Set<Roles> userRoles = Collections.emptySet();
  @JsonIgnore
  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
  private Set<Roles> roles = Collections.emptySet();

  @JsonManagedReference
  @OneToMany(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.ALL},
      mappedBy = "user")
  private Set<Order> userOrders = Collections.emptySet();

  //    public void setRoles(Set<Roles> roles) {
  //        if (this.userRoles==null){
  //            this.userRoles = roles;
  //        } else {
  //            this.userRoles.clear();
  //            if (roles != null) {
  //                this.userRoles.addAll(roles);
  //            }
  //        }

}
