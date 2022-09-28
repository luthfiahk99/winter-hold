package com.indocyber.demo.dto.author;

import java.time.LocalDate;

public class AuthorHeaderDTO {

    private Long id;

    private String fullName;

    private LocalDate birthDate;

    private LocalDate deceasedDate;

    private String education;

    private String summary;

    public AuthorHeaderDTO(){};

    public AuthorHeaderDTO(Long id, String fullName, LocalDate birthDate, LocalDate deceasedDate, String education, String summary) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deceasedDate = deceasedDate;
        this.education = education;
        this.summary = summary;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeceasedDate() {
        return deceasedDate;
    }

    public void setDeceasedDate(LocalDate deceasedDate) {
        this.deceasedDate = deceasedDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
