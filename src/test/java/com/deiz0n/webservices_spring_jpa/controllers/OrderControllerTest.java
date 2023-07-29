package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.models.Order;
import com.deiz0n.webservices_spring_jpa.models.User;
import com.deiz0n.webservices_spring_jpa.models.enums.OrderStatus;
import com.deiz0n.webservices_spring_jpa.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllOrders() {
        var order1 = createOrder(UUID.randomUUID(), Instant.now(), OrderStatus.DELIVERED, null);
        var order2 = createOrder(UUID.randomUUID(), Instant.now(), OrderStatus.PAID, null);
        List<Order> orders = Arrays.asList(order1, order2);
        when(orderService.getAllResources()).thenReturn(orders);
        ResponseEntity<List<Order>> response = orderController.findAllOrders();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    public void testFindOrderById() {
        var orderId = UUID.randomUUID();
        var order = createOrder(orderId, Instant.now(), OrderStatus.WAITING_PAYMENT, null);
        when(orderService.getResource(orderId)).thenReturn(order);
        ResponseEntity<Order> response = orderController.findOrderById(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    private Order createOrder(UUID id, Instant moment, OrderStatus orderStatus, User user) {
        var order = new Order();
        order.setId(id);
        order.setMoment(moment);
        order.setOrderStatus(orderStatus);
        order.setUser(user);
        order.setItems(new HashSet<>());
        return order;
    }
}