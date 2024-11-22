package com.payetonkawa.customer_service.service;

import com.payetonkawa.customer_service.dto.AddressDTO;

public interface AddressService extends GenericService<AddressDTO>{
    AddressDTO update(AddressDTO newDTO, Long id) throws Exception;

}
