package com.example.oauth2.controller;

import com.example.oauth2.entity.Product;
import com.example.oauth2.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    private Product save(@RequestBody Product product){
        System.out.println(product);
        return productRepository.save(product);
    }
    @GetMapping
    private List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
    }
    @GetMapping("/{id}")
    private Optional<Product> findProduct(@PathVariable int id){
        return productRepository.findById(id);
    }
    @DeleteMapping("/{id}")
    private void remove(@PathVariable int id){
        productRepository.deleteById(id);
    }
    @DeleteMapping("/deleteAll")
    private void removeAll(){
        productRepository.deleteAll();
    }
}
