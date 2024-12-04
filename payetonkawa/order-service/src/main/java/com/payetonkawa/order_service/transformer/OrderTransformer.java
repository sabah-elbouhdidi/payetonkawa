package com.payetonkawa.order_service.transformer;

import com.payetonkawa.order_service.dto.OrderDTO;
import com.payetonkawa.order_service.dto.OrderedProductDTO;
import com.payetonkawa.order_service.entity.Order;
import com.payetonkawa.order_service.entity.OrderedProduct;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Component
public class OrderTransformer implements Transformer<OrderDTO, Order>{

    @Autowired
    private OrderedProductTransformer orderedProductTransformer;

    @Override
    public OrderDTO fromEntityToDto(Order order) {
        return OrderDTO.builder()
                .idOrder(order.getId())
                .createdAt(order.getCreatedAt())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .productsDTO(orderedProductTransformer.fromEntityListToDtoList(order.getProduct()))
                .build();
    }

    @Override
    public Order fromDtoToEntity(OrderDTO orderDTO) {
        Order order = Order.builder()
                .id(orderDTO.getIdOrder())
                .createdAt(orderDTO.getCreatedAt())
                .customerId(orderDTO.getCustomerId())
                .totalPrice(orderDTO.getTotalPrice())
                .status(orderDTO.getStatus())
                .build();

        if (orderDTO.getProductsDTO() != null) {
            List<OrderedProduct> products = new ArrayList<>();
            for (OrderedProductDTO productDTO : orderDTO.getProductsDTO()) {
                OrderedProduct product = OrderedProduct.builder()
                        .id(productDTO.getId())
                        .productId(productDTO.getProductId())
                        .quantity(productDTO.getQuantity())
                        .order(order)
                        .build();
                products.add(product);
            }
            order.setProduct(products);
        }

        return order;
    }

    @Override
    public List<OrderDTO> fromEntityListToDtoList(List<Order> orders) {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Order order : orders) {
            orderDTOList.add(this.fromEntityToDto(order));
        }

        return orderDTOList;
    }

    @Override
    public List<Order> fromDtoListToEntityList(List<OrderDTO> orderDTOS) {
        List<Order> orderList = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOS) {
            orderList.add(this.fromDtoToEntity(orderDTO));
        }

        return orderList;
    }
}
