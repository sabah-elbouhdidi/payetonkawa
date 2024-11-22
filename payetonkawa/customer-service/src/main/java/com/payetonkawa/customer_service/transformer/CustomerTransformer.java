package com.payetonkawa.customer_service.transformer;

import com.payetonkawa.customer_service.dto.CustomerDTO;
import com.payetonkawa.customer_service.entity.Customer;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Component
public class CustomerTransformer implements Transformer<CustomerDTO, Customer>{

    @Autowired
    private CompanyTransformer companyTransformer;

    @Autowired
    private AddressTransformer addressTransformer;

    @Override
    public CustomerDTO fromEntityToDto(Customer customer) {
        return CustomerDTO.builder()
                .email(customer.getEmail())
                .pwd(customer.getPwd())
                .id(customer.getId())
                .createdAt(customer.getCreatedAt())
                .companyDTO(companyTransformer.fromEntityToDto(customer.getCompany()))
                .addressDTO(addressTransformer.fromEntityToDto(customer.getAddress()))
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }

    @Override
    public Customer fromDtoToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .email(customerDTO.getEmail())
                .pwd(customerDTO.getPwd())
                .id(customerDTO.getId())
                .createdAt(customerDTO.getCreatedAt())
                .company(companyTransformer.fromDtoToEntity(customerDTO.getCompanyDTO()))
                .address(addressTransformer.fromDtoToEntity(customerDTO.getAddressDTO()))
                .username(customerDTO.getUsername())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .build();
    }

    @Override
    public List<CustomerDTO> fromEntityListToDtoList(List<Customer> customers) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customers) {
            customerDTOList.add(this.fromEntityToDto(customer));
        }

        return customerDTOList;
    }

    @Override
    public List<Customer> fromDtoListToEntityList(List<CustomerDTO> customerDTOS) {
        List<Customer> customerList = new ArrayList<>();

        for (CustomerDTO customerDTO : customerDTOS) {
            customerList.add(this.fromDtoToEntity(customerDTO));
        }

        return customerList;
    }
}
