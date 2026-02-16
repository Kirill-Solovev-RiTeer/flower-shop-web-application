package com.flowershop.service;

import com.flowershop.entity.Product;
import com.flowershop.entity.User;
import com.flowershop.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.flowershop.repository.ProductRepository;
import com.flowershop.repository.UserRepository;

import java.util.Collection;
import java.util.Set;

@Service
public class FavoriteService {

    private final  UserRepository userRepository;
    private final ProductRepository productRepository;
    public FavoriteService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void addToFavorites(int productId) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        user.getFavoriteProducts().add(product);
        userRepository.save(user);
    }

    public Collection<Product> getFavorites() {
        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getFavoriteProducts();
    }

    public void removeFromFavorites(int productId) {
        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        user.getFavoriteProducts().remove(product);
        userRepository.save(user);
    }



}
