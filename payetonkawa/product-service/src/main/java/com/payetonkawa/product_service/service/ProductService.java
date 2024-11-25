package com.payetonkawa.product_service.service;

import com.payetonkawa.product_service.dto.ProductDTO;

public interface ProductService extends GenericService<ProductDTO>{
    ProductDTO update(ProductDTO newDTO, Long idProduct) throws Exception;

}
