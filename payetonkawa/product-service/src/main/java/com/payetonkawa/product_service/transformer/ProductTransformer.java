package com.payetonkawa.product_service.transformer;

import com.payetonkawa.product_service.dto.ProductDTO;
import com.payetonkawa.product_service.entity.Product;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Component
public class ProductTransformer implements Transformer<ProductDTO, Product>{

    @Autowired
    private DetailsTransformer detailsTransformer;
    @Override
    public ProductDTO fromEntityToDto(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .createdAt(product.getCreatedAt())
                .stock(product.getStock())
                .category(product.getCategory())
                .detailsDTO(detailsTransformer.fromEntityToDto(product.getDetails()))
                .build();
    }

    @Override
    public Product fromDtoToEntity(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .createdAt(productDTO.getCreatedAt())
                .stock(productDTO.getStock())
                .category(productDTO.getCategory())
                .details(detailsTransformer.fromDtoToEntity(productDTO.getDetailsDTO()))
                .build();
    }

    @Override
    public List<ProductDTO> fromEntityListToDtoList(List<Product> products) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : products) {
            productDTOList.add(this.fromEntityToDto(product));
        }

        return productDTOList;
    }

    @Override
    public List<Product> fromDtoListToEntityList(List<ProductDTO> productDTOS) {
        List<Product> productList = new ArrayList<>();

        for (ProductDTO productDTO : productDTOS) {
            productList.add(this.fromDtoToEntity(productDTO));
        }

        return productList;
    }
}
