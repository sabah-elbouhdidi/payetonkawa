package com.payetonkawa.customer_service.controller;

import com.payetonkawa.customer_service.dto.AddressDTO;
import com.payetonkawa.customer_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/allAddress")
    public List<AddressDTO> allAddress() throws Exception {
        return addressService.getAll();
    }

    @GetMapping("/showAddress")
    public AddressDTO getAddress(Long id) throws Exception {
        return addressService.get(id);
    }

    @PostMapping("/addAddress")
    public void save(@RequestBody AddressDTO addressDTO) throws Exception {
        addressService.add(addressDTO);
    }

    @PostMapping("/addAddresses")
    public void addAddressList(@RequestBody List<AddressDTO> addressDTOList) throws Exception {
        addressService.addList(addressDTOList);
    }

    @PutMapping("/updateAddress/{idAddress}")
    public AddressDTO updateAddress(@RequestBody AddressDTO newDTO, @PathVariable Long idAddress) throws Exception {
        return addressService.update(newDTO,getAddress(idAddress).getId());
    }

    @DeleteMapping("/deleteAddress/{idAddress}")
    public void deleteAddress(@PathVariable Long idAddress) throws Exception {
        addressService.delete(idAddress);
    }
}
