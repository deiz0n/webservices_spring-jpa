package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.models.Category;
import com.deiz0n.webservices_spring_jpa.services.CategoryService;
import com.deiz0n.webservices_spring_jpa.services.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value ="/categories", produces = {"application/json"})
@Tag(name = "Categorias")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Retorna todos as categorias", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories() {
        List<Category> categories = categoryService.getAllResources();
        return ResponseEntity.ok().body(categories);
    }

    @Operation(summary = "Retorna determinada categoria", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable UUID id) {
        var category = categoryService.getResource(id);
        return ResponseEntity.ok().body(category);
    }
}
