package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.dtos.ProductDTO;
import com.deiz0n.webservices_spring_jpa.models.Product;
import com.deiz0n.webservices_spring_jpa.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( value = "/products")
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


    @GetMapping(value = ("/{id}"))
    public ResponseEntity<Product> findProductById(@PathVariable UUID id) {
        var product = productService.getResource(id);
        return ResponseEntity.ok().body(product);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductDTO productDTO) {
        var product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(productService.createResource(product));
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remProduct(@PathVariable UUID id) {
        productService.removeResource(id);
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody @Valid Product product, ProductDTO productDTO) {
         product = productService.updateResource(id, product);
         BeanUtils.copyProperties(product, productDTO);
         return ResponseEntity.ok().body(product);
    }
}
