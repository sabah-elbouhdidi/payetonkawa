package com.payetonkawa.product_service.transformer;

import com.payetonkawa.product_service.dto.DetailsDTO;
import com.payetonkawa.product_service.entity.Details;
import com.payetonkawa.product_service.repository.ProductRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Component
public class DetailsTransformer implements Transformer<DetailsDTO, Details>{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public DetailsDTO fromEntityToDto(Details details) {
        return DetailsDTO.builder()
                .id(details.getId())
                .color(details.getColor())
                .price(details.getPrice())
                .description(details.getDescription())
                .idProduct(details.getProduct().getId())
                .build();
    }

    @Override
    public Details fromDtoToEntity(DetailsDTO detailsDTO) {
        return Details.builder()
                .id(detailsDTO.getId())
                .color(detailsDTO.getColor())
                .price(detailsDTO.getPrice())
                .description(detailsDTO.getDescription())
                .product(detailsDTO.getIdProduct() != null
                        ? productRepository.findById(detailsDTO.getIdProduct()).orElse(null)
                        : null)
                .build();
    }

    @Override
    public List<DetailsDTO> fromEntityListToDtoList(List<Details> details) {
        List<DetailsDTO> detailsDTOList = new ArrayList<>();

        for (Details detail : details) {
            detailsDTOList.add(this.fromEntityToDto(detail));
        }

        return detailsDTOList;
    }

    @Override
    public List<Details> fromDtoListToEntityList(List<DetailsDTO> detailsDTOS) {
        List<Details> detailsList = new ArrayList<>();

        for (DetailsDTO detailDTO : detailsDTOS) {
            detailsList.add(this.fromDtoToEntity(detailDTO));
        }

        return detailsList;
    }
}
