package com.payetonkawa.product_service.serviceImpl;

import com.payetonkawa.product_service.dto.ProductDTO;
import com.payetonkawa.product_service.entity.Product;
import com.payetonkawa.product_service.repository.DetailsRepository;
import com.payetonkawa.product_service.repository.ProductRepository;
import com.payetonkawa.product_service.service.ProductService;
import com.payetonkawa.product_service.transformer.DetailsTransformer;
import com.payetonkawa.product_service.transformer.ProductTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class ProductServiceImpl extends ServiceGenericImpl<ProductDTO, Product> implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTransformer productTransformer;

    @Autowired
    private DetailsTransformer detailsTransformer;

    @Autowired
    private DetailsRepository detailsRepository;

    @Override
    public ProductDTO update(ProductDTO newDTO, Long idProduct) throws Exception {
        return productTransformer.fromEntityToDto(productRepository.findById(idProduct)
                .map(product -> {
                    product.setCategory(newDTO.getCategory());
                    product.setName(newDTO.getName());
                    product.setStock(newDTO.getStock());
                    product.setDetails(detailsTransformer.fromDtoToEntity(newDTO.getDetailsDTO()));
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newDTO.setId(idProduct);
                    return productRepository.save(productTransformer.fromDtoToEntity(newDTO));
                })
        );
    }
}
