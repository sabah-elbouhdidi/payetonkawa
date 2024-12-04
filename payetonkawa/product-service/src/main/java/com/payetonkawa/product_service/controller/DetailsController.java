package com.payetonkawa.product_service.controller;

import com.payetonkawa.product_service.dto.DetailsDTO;
import com.payetonkawa.product_service.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping("/allDetails")
    public List<DetailsDTO> allDetails() throws Exception {
        return detailsService.getAll();
    }

    @GetMapping("/showDetails")
    public DetailsDTO getDetails(Long id) throws Exception {
        return detailsService.get(id);
    }

    @PostMapping("/addDetails")
    public void save(@RequestBody DetailsDTO detailsDTO) throws Exception {
        detailsService.add(detailsDTO);
    }

    @PostMapping("/addDetailsList")
    public void addDetailsList(@RequestBody List<DetailsDTO> detailsDTOList) throws Exception {
        detailsService.addList(detailsDTOList);
    }

    @PutMapping("/updateDetails/{idDetails}")
    public DetailsDTO updateDetails(@RequestBody DetailsDTO newDTO, @PathVariable Long idDetails) throws Exception {
        return detailsService.update(newDTO,getDetails(idDetails).getId());
    }

    @DeleteMapping("/deleteDetails/{idDetails}")
    public void deleteDetails(@PathVariable Long idDetails) throws Exception {
        detailsService.delete(idDetails);
    }
}
