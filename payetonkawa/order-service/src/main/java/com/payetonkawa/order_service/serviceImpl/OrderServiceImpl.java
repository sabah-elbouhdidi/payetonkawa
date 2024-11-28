package com.payetonkawa.order_service.serviceImpl;

import com.payetonkawa.order_service.dto.OrderDTO;
import com.payetonkawa.order_service.dto.OrderedProductDTO;
import com.payetonkawa.order_service.entity.Order;
import com.payetonkawa.order_service.entity.OrderedProduct;
import com.payetonkawa.order_service.repository.OrderRepository;
import com.payetonkawa.order_service.service.OrderService;
import com.payetonkawa.order_service.service.OrderedProductService;
import com.payetonkawa.order_service.transformer.OrderTransformer;
import com.payetonkawa.order_service.transformer.OrderedProductTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@NoArgsConstructor
public class OrderServiceImpl extends ServiceGenericImpl<OrderDTO, Order> implements OrderService {
    @Autowired
    private OrderTransformer orderTransformer;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedProductService orderedProductService;

    @Override
    public OrderDTO update(OrderDTO newDTO, Long idOrder) throws Exception {
        return orderTransformer.fromEntityToDto(orderRepository.findById(idOrder)
                .map(order -> {
                    order.setStatus(newDTO.getStatus());
                    order.setTotalPrice(newDTO.getTotalPrice());
                    order.setCustomerId(newDTO.getCustomerId());
                    List<OrderedProductDTO> newOrderedProduct=newDTO.getProductsDTO();
                    for(OrderedProductDTO orderedProductDTO : newOrderedProduct){
                        try {
                            orderedProductService.update(orderedProductDTO,orderedProductDTO.getId());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    newDTO.setIdOrder(idOrder);
                    return orderRepository.save(orderTransformer.fromDtoToEntity(newDTO));
                })
        );
    }
}
