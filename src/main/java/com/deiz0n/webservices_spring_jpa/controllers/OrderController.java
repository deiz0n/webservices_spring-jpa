package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.models.Order;
import com.deiz0n.webservices_spring_jpa.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/orders", produces = {"application/json"})
@Tag(name = "Pedidos")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Retorna todos os pedidos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders() {
        List<Order> orders = orderService.getAllResources();
        return ResponseEntity.ok().body(orders);
    }

    @Operation(summary = "Retorna determinado pedido", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable UUID id) {
        var order = orderService.getResource(id);
        return ResponseEntity.ok().body(order);
    }
}
