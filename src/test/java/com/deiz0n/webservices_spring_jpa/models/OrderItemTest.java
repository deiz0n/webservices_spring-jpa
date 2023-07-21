package com.deiz0n.webservices_spring_jpa.models;

import com.deiz0n.webservices_spring_jpa.models.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void testSubTotal() {
        var orderItem = new OrderItem(new Order(), new Product() , 2, 1599.34);
        assertEquals(3198.68, orderItem.subTotal());
    }

    @Test
    void testSubTotal2() {
        var orderItem = new OrderItem(new Order(), new Product() , 46, 193.26);
        assertEquals(8889.96, orderItem.subTotal());
    }

    @Test
    void testSubTotal3() {
        var orderItem = new OrderItem(new Order(), new Product() , 8, 19.26);
        assertNotEquals(154, orderItem.subTotal());
    }
}