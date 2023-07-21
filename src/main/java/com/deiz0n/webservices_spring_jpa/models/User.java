package com.deiz0n.webservices_spring_jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
