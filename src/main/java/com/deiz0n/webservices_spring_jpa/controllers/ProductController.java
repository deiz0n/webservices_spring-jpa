package com.deiz0n.webservices_spring_jpa.controllers;


import com.deiz0n.webservices_spring_jpa.models.Product;
import com.deiz0n.webservices_spring_jpa.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.getAllResourcers();
        return ResponseEntity.ok().body(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable UUID id) {
        var product = productService.getResource(id);
        return ResponseEntity.ok().body(product);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(productService.createResource(product));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remProduct(@PathVariable UUID id) {
        productService.removeResource(id);
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody @Valid Product product) {
         product = productService.updateResource(id, product);
         return ResponseEntity.ok().body(product);
    }
}
