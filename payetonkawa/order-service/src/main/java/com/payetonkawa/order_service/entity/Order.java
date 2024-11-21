package com.payetonkawa.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Order{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idOrder;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Long customerId;

    private double totalPrice;

    @OneToMany(mappedBy="order")
    private List<OrderedProduct> product;
}
