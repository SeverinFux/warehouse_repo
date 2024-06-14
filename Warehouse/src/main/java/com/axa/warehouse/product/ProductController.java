package com.axa.warehouse.product;

import com.axa.warehouse.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    @GetMapping
    ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @PostMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product){
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
