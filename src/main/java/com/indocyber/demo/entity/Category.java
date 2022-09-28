package com.indocyber.demo.entity;

import jdk.dynalink.linker.LinkerServices;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "Name")
    private String name;

    @Column(name = "Floor")
    private int floor;

    @Column(name = "Isle")
    private String isle;

    @Column(name = "Bay")
    private int bay;

    @OneToMany(mappedBy = "categoryName", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Category(){}

    public Category(String name, int floor, String isle, int bay) {
        this.name = name;
        this.floor = floor;
        this.isle = isle;
        this.bay = bay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public int getBay() {
        return bay;
    }

    public void setBay(int bay) {
        this.bay = bay;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
