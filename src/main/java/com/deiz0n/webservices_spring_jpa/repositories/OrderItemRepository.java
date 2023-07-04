package com.deiz0n.webservices_spring_jpa.repositories;

import com.deiz0n.webservices_spring_jpa.models.OrderItem;
import com.deiz0n.webservices_spring_jpa.models.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
