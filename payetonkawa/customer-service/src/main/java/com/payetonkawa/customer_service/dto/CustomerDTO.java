package com.payetonkawa.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    private LocalDateTime createdAt;
    private String email;
    private String pwd;
    private String username;
    private String firstName;
    private String lastName;

    private AddressDTO addressDTO;

    private CompanyDTO companyDTO;

}
