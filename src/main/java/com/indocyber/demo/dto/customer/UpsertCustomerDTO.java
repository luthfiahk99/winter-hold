package com.indocyber.demo.dto.customer;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.Period;

public class UpsertCustomerDTO {

    @NotBlank
    private String membershipNumber;

    @NotBlank
    private String firstName;

    private String lastName;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    private String gender;

    private String phone;

    private String address;

    public UpsertCustomerDTO(){}

    public UpsertCustomerDTO(String membershipNumber, String firstName, String lastName, LocalDate birthDate, String gender, String phone, String address) {
        this.membershipNumber = membershipNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getExpire(){
        LocalDate now = LocalDate.now();
        Period period = Period.ofYears(2);
        LocalDate expire = now.plus(period);
        return expire;
    }
}
