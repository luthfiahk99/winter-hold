package com.indocyber.demo.rest;

import com.indocyber.demo.dto.customer.UpsertCustomerDTO;
import com.indocyber.demo.entity.Customer;
import com.indocyber.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    private ResponseEntity<Customer> addCustomer(@RequestBody UpsertCustomerDTO dto){

        Customer newCustomer = customerService.insertCustomer(dto);
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }
}
