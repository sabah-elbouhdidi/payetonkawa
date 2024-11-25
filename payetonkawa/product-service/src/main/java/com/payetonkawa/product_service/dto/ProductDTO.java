package com.payetonkawa.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String name;

    private DetailsDTO detailsDTO;

    private String category;

    private int stock;
}
