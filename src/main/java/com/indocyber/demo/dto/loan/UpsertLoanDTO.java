package com.indocyber.demo.dto.loan;

import java.time.LocalDate;

public class UpsertLoanDTO {

    private Long id;

    private String customerNumber;

    private String bookCode;

    private LocalDate loanDate;

    private String note;

    public UpsertLoanDTO(){}

    public UpsertLoanDTO(Long id, String customerNumber, String bookCode, LocalDate loanDate, String note) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.bookCode = bookCode;
        this.loanDate = loanDate;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
