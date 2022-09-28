package com.indocyber.demo.dto.customer;

import java.time.LocalDate;

public class CustomerGridDTO {

    private String membershipNumber;

    private String fullName;

    private LocalDate membershipExpiredDate;

    public CustomerGridDTO(){}

    public CustomerGridDTO(String membershipNumber, String fullName, LocalDate membershipExpiredDate) {
        this.membershipNumber = membershipNumber;
        this.fullName = fullName;
        this.membershipExpiredDate = membershipExpiredDate;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getMembershipExpiredDate() {
        return membershipExpiredDate;
    }

    public void setMembershipExpiredDate(LocalDate membershipExpiredDate) {
        this.membershipExpiredDate = membershipExpiredDate;
    }
}
