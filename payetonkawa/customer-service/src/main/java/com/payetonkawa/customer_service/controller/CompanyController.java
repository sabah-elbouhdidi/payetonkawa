package com.payetonkawa.customer_service.controller;

import com.payetonkawa.customer_service.dto.CompanyDTO;
import com.payetonkawa.customer_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/allCompanies")
    public List<CompanyDTO> allCompanies() throws Exception {
        return companyService.getAll();
    }

    @GetMapping("/showCompany")
    public CompanyDTO getCompany(Long id) throws Exception {
        return companyService.get(id);
    }

    @PostMapping("/addCompany")
    public void save(@RequestBody CompanyDTO companyDTO) throws Exception {
        companyService.add(companyDTO);
    }

    @PostMapping("/addCompanies")
    public void addCompanyList(@RequestBody List<CompanyDTO> companyDTOList) throws Exception {
        companyService.addList(companyDTOList);
    }

    @PutMapping("/updateCompany/{idCompany}")
    public CompanyDTO updateCompany(@RequestBody CompanyDTO newDTO, @PathVariable Long idCompany) throws Exception {
        return companyService.update(newDTO,getCompany(idCompany).getId());
    }

    @DeleteMapping("/deleteCompany/{idCompany}")
    public void deleteCompany(@PathVariable Long idCompany) throws Exception {
        companyService.delete(idCompany);
    }

}
