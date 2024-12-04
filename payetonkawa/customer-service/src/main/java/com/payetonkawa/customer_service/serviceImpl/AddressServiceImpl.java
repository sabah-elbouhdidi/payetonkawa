package com.payetonkawa.customer_service.serviceImpl;

import com.payetonkawa.customer_service.dto.AddressDTO;
import com.payetonkawa.customer_service.entity.Address;
import com.payetonkawa.customer_service.repository.AddressRepository;
import com.payetonkawa.customer_service.service.AddressService;
import com.payetonkawa.customer_service.transformer.AddressTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class AddressServiceImpl extends ServiceGenericImpl<AddressDTO, Address> implements AddressService {

    @Autowired
    private AddressTransformer addressTransformer;

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public AddressDTO update(AddressDTO newDTO, Long id) throws Exception {
        return addressTransformer.fromEntityToDto(addressRepository.findById(id)
                .map(address -> {
                    address.setCity(newDTO.getCity());
                    address.setPostalCode(newDTO.getPostalCode());
                    return addressRepository.save(address);
                })
                .orElseGet(() -> {
                    newDTO.setId(id);
                    return addressRepository.save(addressTransformer.fromDtoToEntity(newDTO));
                })
        );
    }
}
