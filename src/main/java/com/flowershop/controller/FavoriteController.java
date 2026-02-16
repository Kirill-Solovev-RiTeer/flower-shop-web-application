package com.flowershop.controller;


import com.flowershop.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
