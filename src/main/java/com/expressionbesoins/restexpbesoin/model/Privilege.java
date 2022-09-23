package com.expressionbesoins.restexpbesoin.model;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name="privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="privilege_id")
    private Long id;
    @Column(name="name")
    @Enumerated(EnumType.STRING)
    private PrivilegeEnum name;



    public Privilege(PrivilegeEnum name) {
        this.name = name;
    }

    public Privilege() {

    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
