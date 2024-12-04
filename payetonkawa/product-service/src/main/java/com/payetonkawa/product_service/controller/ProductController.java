package com.payetonkawa.product_service.controller;

import com.payetonkawa.product_service.dto.ProductDTO;
import com.payetonkawa.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allProducts")
    public List<ProductDTO> allProducts() throws Exception {
        return productService.getAll();
    }

    @GetMapping("/showProduct")
    public ProductDTO getProduct(Long id) throws Exception {
        return productService.get(id);
    }

    @PostMapping("/addProduct")
    public void save(@RequestBody ProductDTO productDTO) throws Exception {
        productService.add(productDTO);
    }

    @PostMapping("/addProducts")
    public void addProductsList(@RequestBody List<ProductDTO> productDTOList) throws Exception {
        productService.addList(productDTOList);
    }

    @PutMapping("/updateProduct/{idProduct}")
    public ProductDTO updateProduct(@RequestBody ProductDTO newDTO, @PathVariable Long idProduct) throws Exception {
        return productService.update(newDTO,getProduct(idProduct).getId());
    }

    @DeleteMapping("/deleteProduct/{idProduct}")
    public void deleteProduct(@PathVariable Long idProduct) throws Exception {
        productService.delete(idProduct);
    }
}
