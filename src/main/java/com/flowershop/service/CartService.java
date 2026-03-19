package com.flowershop.service;

import com.flowershop.entity.*;
import com.flowershop.repository.CartItemRepository;
import com.flowershop.repository.CartRepository;
import com.flowershop.repository.ProductRepository;
import com.flowershop.repository.UserRepository;
import com.flowershop.util.SecurityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       UserRepository userRepository,
                       ProductRepository productRepository,
                       CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    private Cart getUserCart(){
        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();
        return cartRepository.findByUser(user)
                .orElseGet(() ->{
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    public void addProductToCart(int productId){

        Cart cart = getUserCart();
        Product product = productRepository.findById(productId).orElseThrow();
        CartItem item = cartItemRepository.findByCartAndProduct(cart,product).orElse(null);
        if(item == null){
            item = new CartItem(cart, product, 1);
        }
        else{
            item.setQuantity(item.getQuantity() + 1);
        }
        cartItemRepository.save(item);
    }

    public List<CartItem> getCartItems(){

        Cart cart = getUserCart();
        return cartItemRepository.findByCart(cart);
    }

    public void removeFromCart(int productId){

        Cart cart = getUserCart();
        Product product = productRepository.findById(productId).orElseThrow();
        CartItem item = cartItemRepository.findByCartAndProduct(cart,product).orElseThrow();
        cartItemRepository.delete(item);

    }



}
