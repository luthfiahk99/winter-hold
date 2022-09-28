package com.indocyber.demo.dto.book;

import java.time.LocalDate;

public class BookGridDTO {

    private String code;

    private String title;

    private String categoryName;

    private String author;

    private String isBorrowed;

    private LocalDate releaseDate;

    private int totalPage;

    public BookGridDTO(){}

    public BookGridDTO(String code, String title, String categoryName, String author, boolean isBorrowed, LocalDate releaseDate, int totalPage) {
        this.code = code;
        this.title = title;
        this.categoryName = categoryName;
        this.author = author;
        this.isBorrowed = ((isBorrowed == false)? "Available":"Borrowed");
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
    }

    public BookGridDTO(String code, String title, String categoryName) {
        this.code = code;
        this.title = title;
        this.categoryName = categoryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(String borrowed) {
        isBorrowed = borrowed;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
