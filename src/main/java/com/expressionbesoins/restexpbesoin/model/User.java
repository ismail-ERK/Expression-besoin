package com.expressionbesoins.restexpbesoin.model;
/**
 * @autor abdelhadi mouzafir
 */

import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Entity
@Table(name="users" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Setter

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;
    // les informations génirales de l'utilisateur
    @Column(name="username" , nullable = false )
    private String username;
    @Column(name="firstName" , nullable = false )
    private String firstName;
    @Column(name="lastName" , nullable = false )
    private String lastName;
    @NotNull
    @Column(name="email" , nullable = false )
    private String email;
    @NotNull
    @Column(name="password" , nullable = false )
    private String password;
    @Column(name="enabled" , nullable = false )
    private boolean enabled = true;



    // many to many relatioship : user to roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles = new HashSet<Role>();

    // timestamps : creation date
    @CreationTimestamp
    @Column(name="created_at" , nullable = false, updatable = false)
    private Date createdAt;

    public User(Long id, String username, String firstName, String lastName, String email, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        // ? encrypt password
        this.password = encoder.encode(password);
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    // ? password verification
    public boolean isMatchingPassword(String pass) {
        return encoder.matches(pass, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
