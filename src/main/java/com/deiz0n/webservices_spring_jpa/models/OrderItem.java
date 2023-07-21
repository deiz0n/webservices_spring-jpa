package com.deiz0n.webservices_spring_jpa.models;

import com.deiz0n.webservices_spring_jpa.models.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    @EqualsAndHashCode.Include
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;

    public OrderItem(Order order, Product product, Integer quantity){
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
    }
    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public Double subTotal(Product product) {
        return product.getPrice() * getQuantity();
    }

}
