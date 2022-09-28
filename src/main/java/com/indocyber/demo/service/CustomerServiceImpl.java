package com.indocyber.demo.service;

import com.indocyber.demo.dto.customer.CustomerGridDTO;
import com.indocyber.demo.dto.customer.UpsertCustomerDTO;
import com.indocyber.demo.entity.Customer;
import com.indocyber.demo.repository.CustomerRepository;
import com.indocyber.demo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    private int rowsInPage = 5;

    @Override
    public Customer insertCustomer(UpsertCustomerDTO dto) {
        Customer newCustomer = new Customer(
                dto.getMembershipNumber(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getGender(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getExpire()
        );

        newCustomer.setMembershipExpiredDate(dto.getExpire());
//        System.out.println(dto.getExpire());
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    @Override
    public List<CustomerGridDTO> getCustomerGrid(Integer page, String membershipNumber, String fullName) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));

        List<CustomerGridDTO> grid = customerRepository.findAll(membershipNumber, fullName , pagination);

        return grid;
    }

    @Override
    public long getTotalPages(String membershipNumber, String fullName) {
        double totalData = (double)(customerRepository.count(membershipNumber, fullName));

        long totalPage = (long) (Math.ceil(totalData / rowsInPage));

        return totalPage;
    }

    @Override
    public UpsertCustomerDTO getUpdateCustomer(String membershipNumber) {
        Optional<Customer> nullableEntity = customerRepository.findById(membershipNumber);

        Customer entity = nullableEntity.get();
        UpsertCustomerDTO customerDTO = new UpsertCustomerDTO(
                entity.getMembershipNumber(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getGender(),
                entity.getPhone(),
                entity.getAddress()
        );

        return customerDTO;
    }

    @Override
    public Long dependentLoan(String membershipNumber) {
        Long totalDependentLoan = loanRepository.countByCustomer(membershipNumber);

        return totalDependentLoan;
    }

    @Override
    public void deleteById(String membershipNumber) {
        customerRepository.deleteById(membershipNumber);
    }


}
