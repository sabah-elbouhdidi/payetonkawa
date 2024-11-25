package com.payetonkawa.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsDTO {
    private Long id;
    private double price;
    private String description;
    private String color;
    private Long idProduct;
}
