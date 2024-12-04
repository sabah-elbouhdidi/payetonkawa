package com.payetonkawa.order_service.transformer;

import com.payetonkawa.order_service.dto.OrderedProductDTO;
import com.payetonkawa.order_service.entity.OrderedProduct;
import com.payetonkawa.order_service.repository.OrderRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Component
public class OrderedProductTransformer implements Transformer<OrderedProductDTO, OrderedProduct>{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderedProductDTO fromEntityToDto(OrderedProduct orderedProduct) {
        return OrderedProductDTO.builder()
                .id(orderedProduct.getId())
                .productId(orderedProduct.getProductId())
                .quantity(orderedProduct.getQuantity())
                .idOrder(orderedProduct.getOrder().getId())
                .build();
    }

    @Override
    public OrderedProduct fromDtoToEntity(OrderedProductDTO orderedProductDTO) {
        return OrderedProduct.builder()
                .id(orderedProductDTO.getId())
                .productId(orderedProductDTO.getProductId())
                .quantity(orderedProductDTO.getQuantity())
                .order(orderedProductDTO.getIdOrder() != null
                        ? orderRepository.findById(orderedProductDTO.getIdOrder()).orElse(null)
                        : null)
                .build();
    }

    @Override
    public List<OrderedProductDTO> fromEntityListToDtoList(List<OrderedProduct> orderedProducts) {
        List<OrderedProductDTO> orderedProductDTOList = new ArrayList<>();

        for (OrderedProduct orderedProduct : orderedProducts) {
            orderedProductDTOList.add(this.fromEntityToDto(orderedProduct));
        }

        return orderedProductDTOList;
    }

    @Override
    public List<OrderedProduct> fromDtoListToEntityList(List<OrderedProductDTO> orderedProductDTOS) {
        List<OrderedProduct> orderedProductList = new ArrayList<>();

        for (OrderedProductDTO orderedProductDTO : orderedProductDTOS) {
            orderedProductList.add(this.fromDtoToEntity(orderedProductDTO));
        }

        return orderedProductList;
    }
}
