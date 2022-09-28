package com.indocyber.demo.service;

import com.indocyber.demo.dto.customer.CustomerGridDTO;
import com.indocyber.demo.dto.customer.UpsertCustomerDTO;
import com.indocyber.demo.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer insertCustomer(UpsertCustomerDTO dto);

    List<CustomerGridDTO> getCustomerGrid(Integer page, String membershipNumber, String fullName);

    long getTotalPages(String membershipNumber, String fullName);

    UpsertCustomerDTO getUpdateCustomer(String membershipNumber);

    Long dependentLoan(String membershipNumber);

    void deleteById(String membershipNumber);
}
