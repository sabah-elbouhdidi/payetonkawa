package com.payetonkawa.order_service.service;

import com.payetonkawa.order_service.dto.OrderDTO;

public interface OrderService extends GenericService<OrderDTO>{
    OrderDTO update(OrderDTO newDTO, Long idOrder) throws Exception;
}
