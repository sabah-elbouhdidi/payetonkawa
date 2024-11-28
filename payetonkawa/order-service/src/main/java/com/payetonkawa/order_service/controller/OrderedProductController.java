package com.payetonkawa.order_service.controller;

import com.payetonkawa.order_service.dto.OrderedProductDTO;
import com.payetonkawa.order_service.service.OrderedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class OrderedProductController {
    @Autowired
    private OrderedProductService orderedProductService;

    @GetMapping("/allOrderedProducts")
    public List<OrderedProductDTO> allOrderedProducts() throws Exception {
        return orderedProductService.getAll();
    }

    @GetMapping("/showOrderedProduct")
    public OrderedProductDTO getOrderedProduct(Long id) throws Exception {
        return orderedProductService.get(id);
    }

    @PostMapping("/addOrderedProduct")
    public void save(@RequestBody OrderedProductDTO orderedProductDTO) throws Exception {
        orderedProductService.add(orderedProductDTO);
    }

    @PostMapping("/addOrderedProductsList")
    public void addOrderedProductsList(@RequestBody List<OrderedProductDTO> orderedProductDTOList) throws Exception {
        orderedProductService.addList(orderedProductDTOList);
    }

    @PutMapping("/updateOrderedProduct/{idOrderedProduct}")
    public OrderedProductDTO updateOrderedProduct(@RequestBody OrderedProductDTO newDTO, @PathVariable Long idOrderedProduct) throws Exception {
        return orderedProductService.update(newDTO,idOrderedProduct);
    }

    @DeleteMapping("/deleteOrderedProduct/{idOrderedProduct}")
    public void deleteOrderedProduct(@PathVariable Long idOrderedProduct) throws Exception {
        orderedProductService.delete(idOrderedProduct);
    }
}
