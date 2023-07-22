package com.deiz0n.webservices_spring_jpa.models;

import com.deiz0n.webservices_spring_jpa.models.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

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

<<<<<<< HEAD
    public OrderItem(Order order, Product product, Integer quantity, Double price){
=======
    public OrderItem(Order order, Product product, Integer quantity){
>>>>>>> eb1b5756383a7798312730358742814fcd963c80
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
    }
    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

<<<<<<< HEAD
    public Double subTotal() {
        return getPrice() * getQuantity();
=======
    public Double subTotal(Product product) {
        return product.getPrice() * getQuantity();
>>>>>>> eb1b5756383a7798312730358742814fcd963c80
    }

}
