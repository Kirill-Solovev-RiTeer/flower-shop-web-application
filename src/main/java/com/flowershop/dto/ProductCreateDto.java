package com.flowershop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductCreateDto {

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private int price;

    public ProductCreateDto(){}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }


}
