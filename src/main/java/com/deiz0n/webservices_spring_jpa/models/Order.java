package com.deiz0n.webservices_spring_jpa.models;

import com.deiz0n.webservices_spring_jpa.models.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Instant moment;
    private OrderStatus orderStatus;

    @ManyToOne
    private User user;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order(Instant moment, OrderStatus orderStatus, User user) {
        this.moment = moment;
        this.user = user;
        setOrderStatus(orderStatus);
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(String.valueOf(orderStatus));
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = OrderStatus.valueOf(orderStatus.getCode());
        }
    }

    /*
    public Double getTotalValue() {
        double sum = 0.0;
        for (OrderItem value : items) {
            sum += value.subTotal();
        }
        return sum;
    }
     */

}
