package com.indocyber.demo.service;

import com.indocyber.demo.dto.loan.UpsertLoanDTO;
import com.indocyber.demo.entity.Loan;
import com.indocyber.demo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan insertLoan(UpsertLoanDTO dto) {
        Loan newLoan = new Loan(
                dto.getId(),
                dto.getCustomerNumber(),
                dto.getBookCode(),
                dto.getLoanDate(),
                dto.getNote()
        );

        loanRepository.save(newLoan);
        return newLoan;
    }
}
