package com.payetonkawa.customer_service.serviceImpl;

import com.payetonkawa.customer_service.dto.CompanyDTO;
import com.payetonkawa.customer_service.entity.Company;
import com.payetonkawa.customer_service.repository.CompanyRepository;
import com.payetonkawa.customer_service.service.CompanyService;
import com.payetonkawa.customer_service.transformer.CompanyTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
public class CompanyServiceImp extends ServiceGenericImpl<CompanyDTO, Company> implements CompanyService {

    @Autowired
    private CompanyTransformer companyTransformer;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDTO update(CompanyDTO newDTO, Long id) throws Exception {
        return companyTransformer.fromEntityToDto(companyRepository.findById(id)
                .map(company -> {
                    company.setCompanyname(newDTO.getCompanyname());
                    return companyRepository.save(company);
                })
                .orElseGet(() -> {
                    newDTO.setId(id);
                    return companyRepository.save(companyTransformer.fromDtoToEntity(newDTO));
                })
        );
    }
}
