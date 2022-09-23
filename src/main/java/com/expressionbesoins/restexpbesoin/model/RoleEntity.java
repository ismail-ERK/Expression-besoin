package com.expressionbesoins.restexpbesoin.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column
    String role;
}
