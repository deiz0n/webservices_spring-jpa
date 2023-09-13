package com.deiz0n.webservices_spring_jpa.controllers;


import com.deiz0n.webservices_spring_jpa.models.Product;
import com.deiz0n.webservices_spring_jpa.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = "/products", produces = {"application/json"})
@Tag(name = "Produtos")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Retorna todos os produtos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.getAllResourcers();
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Retorna determinado produto", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable UUID id) {
        var product = productService.getResource(id);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Cadastra um novo produto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campo preenchido incorretamente")
    })
    @Transactional
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(productService.createResource(product));
    }

    @Operation(summary = "Deleta determinado produto", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "409", description = "Produto vinculado à um pedido")
    })
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remProduct(@PathVariable UUID id) {
        productService.removeResource(id);
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualiza os dados de determinado produto", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody @Valid Product product) {
         product = productService.updateResource(id, product);
         return ResponseEntity.ok().body(product);
    }
}
