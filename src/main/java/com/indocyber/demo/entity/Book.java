package com.indocyber.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "Code")
    private String code;

    @Column(name = "Title")
    private String title;

    @Column(name = "CategoryName")
    private String categoryName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CategoryName", insertable = false, updatable = false)
    private Category category;

    @Column(name = "AuthorId")
    private Long authorId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "AuthorId", insertable = false, updatable = false)
    private Author author;

    @Column(name = "IsBorrowed")
    private Boolean isBorrowed;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "ReleaseDate")
    private LocalDate releaseDate;

    @Column(name = "TotalPage")
    private int totalPage;

    public Book(){}

    public Book(String code, String title, String categoryName, Long authorId, String summary, LocalDate releaseDate,
                int totalPage) {
        this.code = code;
        this.title = title;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
    }

    public Book(String code, String title, String categoryName, Long authorId, Boolean isBorrowed, String summary, LocalDate releaseDate,
                int totalPage) {
        this.code = code;
        this.title = title;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.isBorrowed = isBorrowed;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(Boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
