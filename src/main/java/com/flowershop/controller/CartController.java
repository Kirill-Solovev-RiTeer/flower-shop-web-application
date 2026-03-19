package com.flowershop.controller;


import com.flowershop.entity.Cart;
import com.flowershop.entity.CartItem;
import com.flowershop.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartItem> getCart(){
        return cartService.getCartItems();
    }

    @PostMapping("/{productId}")
    public void addCartItem(@PathVariable int productId){
        cartService.addProductToCart(productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteCartItem(@PathVariable int productId){
        cartService.removeFromCart(productId);
    }

}
