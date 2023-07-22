package com.deiz0n.webservices_spring_jpa.models;

import com.deiz0n.webservices_spring_jpa.models.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
<<<<<<< HEAD
    void testMethodSubTotal() {
        var orderItem = new OrderItem(new Order(), new Product(), 2, 738.99);
        assertEquals(1477.98, orderItem.subTotal());
    }

    @Test
    void testTwoMethodSubTotal() {
        var orderItem = new OrderItem(new Order(), new Product(), 33, 237.88);
        assertEquals(7850.04, orderItem.subTotal());
=======
    void testSubTotal() {
        var product = new Product();
        product.setPrice(198.23);
        var orderItem = new OrderItem(new Order(), product, 2);
        assertEquals(396.46, orderItem.subTotal(product));
        assertNotEquals(396, orderItem.subTotal(product));
    }

    @Test
    void testSubTotal2() {
        var product = new Product();
        product.setPrice(77.25);
        var orderItem = new OrderItem(new Order(), product, 33);
        assertEquals(2549.25, orderItem.subTotal(product));
        assertFalse(orderItem.subTotal(product) == 2549);
    }

    @Test
    void testSubTotal3() {
        var product = new Product();
        product.setPrice(95.19);
        var orderItem = new OrderItem(new Order(), product, 25);
        assertTrue(orderItem.subTotal(product) == 2379.75);
        assertFalse(orderItem.subTotal(product) == 2379.7);
>>>>>>> eb1b5756383a7798312730358742814fcd963c80
    }

}