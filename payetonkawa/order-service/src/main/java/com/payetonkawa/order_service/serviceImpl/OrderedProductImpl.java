package com.payetonkawa.order_service.serviceImpl;

import com.payetonkawa.order_service.dto.OrderedProductDTO;
import com.payetonkawa.order_service.entity.OrderedProduct;
import com.payetonkawa.order_service.repository.OrderRepository;
import com.payetonkawa.order_service.repository.OrderedProductRepository;
import com.payetonkawa.order_service.service.OrderedProductService;
import com.payetonkawa.order_service.transformer.OrderedProductTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class OrderedProductImpl extends ServiceGenericImpl<OrderedProductDTO, OrderedProduct> implements OrderedProductService {
    @Autowired
    private OrderedProductTransformer orderedProductTransformer;
    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderedProductDTO update(OrderedProductDTO newDTO, Long idOrderedProduct) throws Exception {
        return orderedProductTransformer.fromEntityToDto(orderedProductRepository.findById(idOrderedProduct)
                .map(orderedProduct -> {
                    orderedProduct.setQuantity(newDTO.getQuantity());
                    orderedProduct.setProductId(newDTO.getProductId());
                    orderedProduct.setOrder(orderRepository.findById(newDTO.getIdOrder()).get());
                    return orderedProductRepository.save(orderedProduct);
                })
                .orElseGet(() -> {
                    newDTO.setIdOrder(idOrderedProduct);
                    return orderedProductRepository.save(orderedProductTransformer.fromDtoToEntity(newDTO));
                })
        );
    }
}
