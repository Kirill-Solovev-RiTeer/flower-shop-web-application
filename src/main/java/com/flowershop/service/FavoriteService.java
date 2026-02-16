package com.flowershop.service;

import com.flowershop.dto.ProductResponseDto;
import com.flowershop.entity.Product;
import com.flowershop.entity.User;
import com.flowershop.mapper.ProductMapper;
import com.flowershop.util.SecurityUtils;
import org.springframework.stereotype.Service;
import com.flowershop.repository.ProductRepository;
import com.flowershop.repository.UserRepository;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final  UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productMapper;

    public FavoriteService(UserRepository userRepository, ProductRepository productRepository, ProductService productMapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
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

    public Set<ProductResponseDto> getFavorites() {
        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getFavoriteProducts()
                .stream()
                .map(ProductMapper::mapToDto)
                .collect(Collectors.toSet());
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
