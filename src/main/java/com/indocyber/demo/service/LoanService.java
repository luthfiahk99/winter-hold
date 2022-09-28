package com.indocyber.demo.service;


import com.indocyber.demo.dto.loan.UpsertLoanDTO;
import com.indocyber.demo.entity.Loan;

public interface LoanService {

    Loan insertLoan(UpsertLoanDTO dto);
}
