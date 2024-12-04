package com.payetonkawa.customer_service.service;

import com.payetonkawa.customer_service.dto.CompanyDTO;

public interface CompanyService extends GenericService<CompanyDTO> {
    CompanyDTO update(CompanyDTO newDTO, Long id) throws Exception;

}
