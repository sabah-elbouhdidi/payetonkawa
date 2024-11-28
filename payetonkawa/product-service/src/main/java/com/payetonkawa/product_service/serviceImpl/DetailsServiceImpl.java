package com.payetonkawa.product_service.serviceImpl;

import com.payetonkawa.product_service.dto.DetailsDTO;
import com.payetonkawa.product_service.entity.Details;
import com.payetonkawa.product_service.repository.DetailsRepository;
import com.payetonkawa.product_service.repository.ProductRepository;
import com.payetonkawa.product_service.service.DetailsService;
import com.payetonkawa.product_service.transformer.DetailsTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Data
@NoArgsConstructor
public class DetailsServiceImpl  extends ServiceGenericImpl<DetailsDTO, Details> implements DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    @Autowired
    private DetailsTransformer detailsTransformer;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public DetailsDTO update(DetailsDTO newDTO, Long idDetails) throws Exception {
        return detailsTransformer.fromEntityToDto(detailsRepository.findById(idDetails)
                .map(details -> {
                    details.setColor(newDTO.getColor());
                    details.setDescription(newDTO.getDescription());
                    details.setPrice(newDTO.getPrice());
                    details.setProduct(productRepository.findById(newDTO.getIdProduct()).get());
                    return detailsRepository.save(details);
                })
                .orElseGet(() -> {
                    newDTO.setId(idDetails);
                    return detailsRepository.save(detailsTransformer.fromDtoToEntity(newDTO));
                })
        );
    }
}
