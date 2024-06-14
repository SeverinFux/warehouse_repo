package com.axa.warehouse.product;

import com.axa.warehouse.exceptionHandler.NotFoundException;
import com.axa.warehouse.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Product> getAllProduct() {

        return productRepository.findAll();

    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    public Product updateProduct(Integer id, Product productIn) {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("No Product found: " + id));
        product.setName(productIn.getName());
        productRepository.save(product);
        return productRepository.findById(id).get();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId).get();
        productRepository.delete(product);
    }
}
