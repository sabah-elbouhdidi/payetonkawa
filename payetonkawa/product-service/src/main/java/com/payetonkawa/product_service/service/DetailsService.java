package com.payetonkawa.product_service.service;

import com.payetonkawa.product_service.dto.DetailsDTO;

public interface DetailsService extends GenericService<DetailsDTO>{
    DetailsDTO update(DetailsDTO newDTO, Long idDetails)throws Exception;


}
