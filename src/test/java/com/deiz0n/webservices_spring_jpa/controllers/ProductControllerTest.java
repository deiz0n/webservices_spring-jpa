package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.dtos.ProductDTO;
import com.deiz0n.webservices_spring_jpa.models.Product;
import com.deiz0n.webservices_spring_jpa.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllProducts() {
        // Arrange
        Product product1 = createProduct(UUID.randomUUID(), "Product 1", "Description 1", 10.0);
        Product product2 = createProduct(UUID.randomUUID(), "Product 2", "Description 2", 20.0);
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllResourcers()).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.findAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    public void testFindProductById() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product product = createProduct(productId, "Product Test", "Description Test", 30.0);
        when(productService.getResource(productId)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.findProductById(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testAddProduct() {
        // Arrange
        ProductDTO productDTO = new ProductDTO("Simples Produto", "Produto", 50.0);

        Product savedProduct = new Product();
        savedProduct.setId(UUID.randomUUID());
        savedProduct.setName("Sample Product");
        savedProduct.setPrice(50.0); // Set other properties as needed

        when(productService.createResource(any(Product.class))).thenReturn(savedProduct);

        // Act
        //ResponseEntity<Product> response = productController.addProduct(productDTO);
        ResponseEntity<Product> responseEntity = productController.addProduct(productDTO);

        // Assert
        verify(productService, times(1)).createResource(any(Product.class));
        verifyNoMoreInteractions(productService);

        assertNotNull(responseEntity);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertTrue(responseEntity.getHeaders().containsKey("Location"));
        assertTrue(responseEntity.getBody() instanceof Product);
        assertEquals(savedProduct, responseEntity.getBody());

        // Check if the "Location" header contains the correct URI
        String expectedUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUriString();
        assertTrue(responseEntity.getHeaders().get("Location").get(0).contains(expectedUri));
    }

    @Test
    public void testRemProduct() {
        var productId = UUID.randomUUID();
        ResponseEntity<Void> response = productController.remProduct(productId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).removeResource(productId);
    }

    @Test
    public void testUpdate() {
        var productId = UUID.randomUUID();
        var productDTO = new ProductDTO("Updated Product", "Updated Description", 50.0);
        var product = createProduct(productId, productDTO.name(), productDTO.description(), productDTO.price());
        when(productService.updateResource(eq(productId), any(Product.class))).thenReturn(product);
        ResponseEntity<Product> response = productController.update(productId, new Product(), productDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    private Product createProduct(UUID id, String name, String description, Double price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }

}