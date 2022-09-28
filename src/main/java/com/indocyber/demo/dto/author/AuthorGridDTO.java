package com.indocyber.demo.dto.author;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AuthorGridDTO {

    private Long id;

    private String fullName;

    private Long age;

    private String status;

    private String education;

    public AuthorGridDTO(){};

    public AuthorGridDTO(Long id, String fullName, String education) {
        this.id = id;
        this.fullName = fullName;
        this.education = education;
    }

    public AuthorGridDTO(Long id, String fullName, LocalDate birthDate, LocalDate deceasedDate, String education) {
        this.id = id;
        this.fullName = fullName;
        this.age = ((deceasedDate == null)? ChronoUnit.YEARS.between(birthDate, LocalDate.now()) : ChronoUnit.YEARS.between(birthDate, deceasedDate));
        this.status = ((deceasedDate == null)? "Alive":"Deceased");
        this.education = education;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
