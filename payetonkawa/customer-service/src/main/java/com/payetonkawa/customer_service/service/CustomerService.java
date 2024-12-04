package com.payetonkawa.customer_service.service;

import com.payetonkawa.customer_service.dto.CustomerDTO;

public interface CustomerService extends GenericService<CustomerDTO>{
    CustomerDTO update(CustomerDTO newDTO, CustomerDTO oldDTO) throws Exception;
}
