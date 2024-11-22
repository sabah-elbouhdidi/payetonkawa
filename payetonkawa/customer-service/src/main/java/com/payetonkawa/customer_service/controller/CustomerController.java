package com.payetonkawa.customer_service.controller;

import com.payetonkawa.customer_service.dto.CustomerDTO;
import com.payetonkawa.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<CustomerDTO> allCustomers() throws Exception {
        return customerService.getAll();
    }

    @GetMapping("/showCustomer")
    public CustomerDTO getCustomer(Long id) throws Exception {
        return customerService.get(id);
    }

    @PostMapping("/addCustomer")
    public void save(@RequestBody CustomerDTO customerDTO) throws Exception {
        customerService.add(customerDTO);
    }

    @PostMapping("/addCustomers")
    public void addCustomerList(@RequestBody List<CustomerDTO> customerDTOList) throws Exception {
        customerService.addList(customerDTOList);
    }

    @PutMapping("/updateCustomer/{idCustomer}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO newDTO, @PathVariable Long idCustomer) throws Exception {
        return customerService.update(newDTO,getCustomer(idCustomer));
    }

    @DeleteMapping("/deleteCustomer/{idCustomer}")
    public void deleteCustomer(@PathVariable Long idCustomer) throws Exception {
        customerService.delete(idCustomer);
    }

}
