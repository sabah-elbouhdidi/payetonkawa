package com.payetonkawa.order_service.service;

import com.payetonkawa.order_service.dto.OrderedProductDTO;

public interface OrderedProductService extends GenericService<OrderedProductDTO>{
    OrderedProductDTO update(OrderedProductDTO newDTO, Long idOrderedProduct) throws Exception;
}
