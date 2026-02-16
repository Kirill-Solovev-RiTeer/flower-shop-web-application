package com.flowershop.mapper;


import com.flowershop.dto.ProductResponseDto;
import com.flowershop.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductResponseDto mapToDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
