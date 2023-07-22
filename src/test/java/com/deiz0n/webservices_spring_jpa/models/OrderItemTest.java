package com.deiz0n.webservices_spring_jpa.models;

import com.deiz0n.webservices_spring_jpa.models.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void testMethodSubTotal() {
        var orderItem = new OrderItem(new Order(), new Product(), 2, 738.99);
        assertEquals(1477.98, orderItem.subTotal());
    }

    @Test
    void testTwoMethodSubTotal() {
        var orderItem = new OrderItem(new Order(), new Product(), 33, 237.88);
        assertEquals(7850.04, orderItem.subTotal());
    }

}