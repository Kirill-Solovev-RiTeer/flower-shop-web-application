package com.flowershop.service;

import com.flowershop.dto.ProductCreateDto;
import com.flowershop.exception.ProductNotFoundException;
import com.flowershop.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private int nextId = 1;


    public ProductService(){
        products.add(new Product(nextId++, "Роза", 200));
        products.add(new Product(nextId++, "Тюльпан", 100));
        products.add(new Product(nextId++, "Лилия", 150));
    }


    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    public Product createProduct(ProductCreateDto dto){
        Product product = new Product(nextId++, dto.getName(), dto.getPrice());
        products.add(product);
        return product;
    }

}
