package com.indocyber.demo.rest;

import com.indocyber.demo.dto.loan.UpsertLoanDTO;
import com.indocyber.demo.entity.Loan;
import com.indocyber.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan")
public class LoanRestController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> addLoan(@RequestBody UpsertLoanDTO dto){

        Loan newLoan = loanService.insertLoan(dto);
        return new ResponseEntity<>(newLoan, HttpStatus.OK);
    }
}
