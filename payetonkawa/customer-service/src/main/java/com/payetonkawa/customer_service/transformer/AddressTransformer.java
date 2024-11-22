package com.payetonkawa.customer_service.transformer;

import com.payetonkawa.customer_service.dto.AddressDTO;
import com.payetonkawa.customer_service.entity.Address;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@Component
public class AddressTransformer implements Transformer<AddressDTO, Address> {
    @Override
    public AddressDTO fromEntityToDto(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }

    @Override
    public Address fromDtoToEntity(AddressDTO addressDTO) {
        return Address.builder()
                .id(addressDTO.getId())
                .city(addressDTO.getCity())
                .postalCode(addressDTO.getPostalCode())
                .build();
    }

    @Override
    public List<AddressDTO> fromEntityListToDtoList(List<Address> addresses) {
        List<AddressDTO> addressDTOList = new ArrayList<>();

        for (Address address : addresses) {
            addressDTOList.add(this.fromEntityToDto(address));
        }

        return addressDTOList;
    }

    @Override
    public List<Address> fromDtoListToEntityList(List<AddressDTO> addressDTOS) {
        List<Address> addressList = new ArrayList<>();

        for (AddressDTO addressDTO : addressDTOS) {
            addressList.add(this.fromDtoToEntity(addressDTO));
        }

        return addressList;
    }
}
