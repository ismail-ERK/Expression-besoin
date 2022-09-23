package com.expressionbesoins.restexpbesoin.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column
    String username;
    @Column
    String password;
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<RoleEntity> roles = new ArrayList<>();
}
