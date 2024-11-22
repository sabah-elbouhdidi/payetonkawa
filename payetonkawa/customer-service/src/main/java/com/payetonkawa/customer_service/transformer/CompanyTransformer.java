package com.payetonkawa.customer_service.transformer;

import com.payetonkawa.customer_service.dto.CompanyDTO;
import com.payetonkawa.customer_service.entity.Company;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Component
public class CompanyTransformer implements Transformer<CompanyDTO, Company>{
    @Override
    public CompanyDTO fromEntityToDto(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .companyname(company.getCompanyname())
                .build();
    }

    @Override
    public Company fromDtoToEntity(CompanyDTO companyDTO) {
        return Company.builder()
                .id(companyDTO.getId())
                .companyname(companyDTO.getCompanyname())
                .build();
    }

    @Override
    public List<CompanyDTO> fromEntityListToDtoList(List<Company> companies) {
        List<CompanyDTO> companyDTOList = new ArrayList<>();

        for (Company company : companies) {
            companyDTOList.add(this.fromEntityToDto(company));
        }

        return companyDTOList;
    }

    @Override
    public List<Company> fromDtoListToEntityList(List<CompanyDTO> companyDTOS) {
        List<Company> companyList = new ArrayList<>();

        for (CompanyDTO companyDTO : companyDTOS) {
            companyList.add(this.fromDtoToEntity(companyDTO));
        }

        return companyList;
    }
}
