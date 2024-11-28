package com.payetonkawa.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long idOrder;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Long customerId;

    private double totalPrice;

    private String status;

    private List<OrderedProductDTO> productsDTO;
}
