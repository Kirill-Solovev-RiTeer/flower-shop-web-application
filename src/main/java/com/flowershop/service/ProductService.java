package com.flowershop.service;

import com.flowershop.dto.ProductCreateDto;
import com.flowershop.exception.ProductNotFoundException;
import com.flowershop.entity.Product;
import org.springframework.stereotype.Service;
import com.flowershop.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    public Product createProduct(ProductCreateDto dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return productRepository.save(product);
    }

}
