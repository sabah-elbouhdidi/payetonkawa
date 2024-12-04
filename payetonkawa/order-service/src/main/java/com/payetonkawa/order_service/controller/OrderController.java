package com.payetonkawa.order_service.controller;

import com.payetonkawa.order_service.dto.OrderDTO;
import com.payetonkawa.order_service.dto.OrderedProductDTO;
import com.payetonkawa.order_service.entity.Order;
import com.payetonkawa.order_service.repository.OrderRepository;
import com.payetonkawa.order_service.service.OrderService;
import com.payetonkawa.order_service.service.OrderedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/allOrders")
    public List<OrderDTO> allOrders() throws Exception {
        return orderService.getAll();
    }

    @GetMapping("/showOrder")
    public OrderDTO getOrder(Long id) throws Exception {
        return orderService.get(id);
    }

    @PostMapping("/addOrder")
    public void save(@RequestBody OrderDTO orderDTO) throws Exception {
        orderService.add(orderDTO);

    }

    @PostMapping("/addOrdersList")
    public void addOrdersList(@RequestBody List<OrderDTO> ordersDTOList) throws Exception {
        orderService.addList(ordersDTOList);
    }

    @PutMapping("/updateOrder/{idOrder}")
    public OrderDTO updateOrder(@RequestBody OrderDTO newDTO, @PathVariable Long idOrder) throws Exception {
        return orderService.update(newDTO,idOrder);
    }

    @DeleteMapping("/deleteOrder/{idOrder}")
    public void deleteOrder(@PathVariable Long idOrder) throws Exception {
        orderService.delete(idOrder);
    }
}
