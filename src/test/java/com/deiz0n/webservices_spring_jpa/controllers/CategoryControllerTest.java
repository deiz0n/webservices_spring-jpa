package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.models.Category;
import com.deiz0n.webservices_spring_jpa.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllCategories() {
        var category1 = new Category("Category 1");
        var category2 = new Category("Category 2");
        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryService.getAllResources()).thenReturn(categories);
        ResponseEntity<List<Category>> response = categoryController.findAllCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    @Test
    public void testFindCategoryById() {
        var categoryId = UUID.randomUUID();
        var category = new Category("Category Test");
        when(categoryService.getResource(categoryId)).thenReturn(category);
        ResponseEntity<Category> response = categoryController.findCategoryById(categoryId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }
}