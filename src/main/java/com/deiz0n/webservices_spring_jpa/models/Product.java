package com.deiz0n.webservices_spring_jpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product  implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String imgURL;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    public Product(String name, String description, Double price, String imgURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgURL = imgURL;
    }

    @JsonIgnore
    public Set<Order> getOrders() {
        Set<Order> orders = new HashSet<>();
        for (OrderItem i : items) {
            orders.add(i.getOrder());
        }
        return orders;
    }
}
