package com.flowershop.controller;

import com.flowershop.dto.LoginRequest;
import com.flowershop.dto.LoginResponse;
import com.flowershop.dto.UserRegisterRequest;
import com.flowershop.entity.User;
import com.flowershop.service.JwtService;
import com.flowershop.service.UserService;
import com.flowershop.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody UserRegisterRequest request){
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){
        User user = userService.authenticate(
                request.getEmail(),
                request.getPassword()
        );
        String token  = jwtService.generateToken(user);
        return new LoginResponse(token);

    }
}
