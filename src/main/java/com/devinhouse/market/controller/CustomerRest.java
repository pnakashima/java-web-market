package com.devinhouse.market.controller;

import com.devinhouse.market.model.transport.CustomerDTO;
import com.devinhouse.market.model.transport.ProductDTO;
import com.devinhouse.market.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerRest {

    private final CustomerService customerService;

    public CustomerRest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody CustomerDTO customerDTO){
        return this.customerService.create(customerDTO);
    }

    @PutMapping("/update/{identifier}")
    public ResponseEntity<HttpStatus> update(@RequestBody CustomerDTO customerDTO, @PathVariable String identifier) {
        return customerDTO.update(customerDTO, identifier);
    }

//    @GetMapping("/list")
//    public List<ProductDTO> listAll() {
//        return this.productService.listAll();
//    }
//
//    @DeleteMapping("/delete/{identifier}")
//    public ResponseEntity<HttpStatus> delete(@PathVariable String identifier) {
//        return this.productService.delete(identifier);
//    }
//
//    @GetMapping("/{identifier}")
//    public ProductDTO getProduct(@PathVariable String identifier) throws Exception {
//        return this.productService.getProductByIdentifier(identifier);
}
