package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.models.Order;
import com.deiz0n.webservices_spring_jpa.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders() {
        List<Order> orders = orderService.getAllResources();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable UUID id) {
        var order = orderService.getResource(id);
        return ResponseEntity.ok().body(order);
    }
}
