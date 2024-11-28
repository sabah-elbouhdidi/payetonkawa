package com.payetonkawa.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductDTO {
    private Long id;

    private Long productId;

    private int quantity;

    private Long idOrder;
}
