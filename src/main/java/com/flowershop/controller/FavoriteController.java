package com.flowershop.controller;


import com.flowershop.entity.Product;
import com.flowershop.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<?> addToFavoriteS(@PathVariable int productId) {

        favoriteService.addToFavorites(productId);
        return ResponseEntity.ok("Added to favorites");
    }

    @GetMapping
    public ResponseEntity<Collection<Product>> getFavoriteProducts() {
        return ResponseEntity.ok(favoriteService.getFavorites());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeFromFavoriteS(@PathVariable int productId) {
        favoriteService.removeFromFavorites(productId);
        return ResponseEntity.ok("Removed from favorites");
    }


}
