package com.payetonkawa.customer_service.serviceImpl;

import com.payetonkawa.customer_service.dto.CustomerDTO;
import com.payetonkawa.customer_service.entity.Address;
import com.payetonkawa.customer_service.entity.Customer;
import com.payetonkawa.customer_service.repository.AddressRepository;
import com.payetonkawa.customer_service.repository.CustomerRepository;
import com.payetonkawa.customer_service.service.CompanyService;
import com.payetonkawa.customer_service.service.CustomerService;
import com.payetonkawa.customer_service.transformer.AddressTransformer;
import com.payetonkawa.customer_service.transformer.CompanyTransformer;
import com.payetonkawa.customer_service.transformer.CustomerTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class CustomerServiceImpl extends ServiceGenericImpl<CustomerDTO, Customer> implements CustomerService {

    @Autowired
    private CustomerTransformer customerTransformer;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyTransformer companyTransformer;

    @Autowired
    private AddressTransformer addressTransformer;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public CustomerDTO update(CustomerDTO newDTO, CustomerDTO oldDto) throws Exception {
        System.out.println("hi : "+oldDto.toString());
        return customerTransformer.fromEntityToDto(customerRepository.findById(oldDto.getId())
                .map(customer -> {
                    customer.setFirstName(newDTO.getFirstName());
                    customer.setLastName(newDTO.getLastName());
                    customer.setEmail(newDTO.getEmail());
                    customer.setPwd(newDTO.getPwd());

                    // Update Address
                    if (newDTO.getAddressDTO() != null && oldDto.getAddressDTO() != null) {
                        Address updatedAddress = addressRepository.findById(oldDto.getAddressDTO().getId())
                                .map(address -> {
                                    address.setCity(newDTO.getAddressDTO().getCity());
                                    address.setPostalCode(newDTO.getAddressDTO().getPostalCode());
                                    return addressRepository.save(address);
                                })
                                .orElseThrow(() -> new RuntimeException("Address not found"));
                        customer.setAddress(updatedAddress);
                    }

                    // Update Company
                    if (newDTO.getCompanyDTO() != null && oldDto.getCompanyDTO() != null) {
                        try {
                            customer.setCompany(companyTransformer.fromDtoToEntity(
                                    companyService.update(newDTO.getCompanyDTO(), oldDto.getCompanyDTO().getId())
                            ));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    customer.setUsername(newDTO.getUsername());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newDTO.setId(oldDto.getId());
                    return customerRepository.save(customerTransformer.fromDtoToEntity(newDTO));
                })
        );
    }
}
