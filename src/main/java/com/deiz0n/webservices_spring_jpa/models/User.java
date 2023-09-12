package com.deiz0n.webservices_spring_jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @Column(unique = true)
    private String email;
    private String address;

    @Column(unique = true)
    private String phone;
    private String password;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public User(String name, String email, String address, String phone, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }

}
