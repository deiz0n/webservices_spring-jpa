package com.deiz0n.webservices_spring_jpa.services;

import com.deiz0n.webservices_spring_jpa.models.Product;
import com.deiz0n.webservices_spring_jpa.repositories.ProductRepository;
import com.deiz0n.webservices_spring_jpa.services.exceptions.DatabaseException;
import com.deiz0n.webservices_spring_jpa.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements ServiceCRUD<Product> {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllResourcers() {
        return productRepository.findAll();
    }

    @Override
    public Product getResource(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Product createResource(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void removeResource(UUID id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException(error.getMessage());
        }
    }

    @Override
    public Product updateResource(UUID id, Product newResourceData) {
        try{
            var oldProductData = productRepository.getReferenceById(id);
            updateDataResource(oldProductData, newResourceData);
            return productRepository.save(newResourceData);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public void updateDataResource(Product oldResourceData, Product newResourceData) {
        oldResourceData.setName(newResourceData.getName());
        oldResourceData.setDescription(newResourceData.getDescription());
        oldResourceData.setPrice(newResourceData.getPrice());
        oldResourceData.setImgURL(newResourceData.getImgURL());
    }
}
