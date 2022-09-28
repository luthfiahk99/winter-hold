package com.indocyber.demo.dto.category;

public class CategoryGridDTO {
    private String name;

    private int floor;

    private String isle;

    private int bay;

    private Long totalBook;

    public CategoryGridDTO(){}

    public CategoryGridDTO(String name, int floor, String isle, int bay) {
        this.name = name;
        this.floor = floor;
        this.isle = isle;
        this.bay = bay;
    }

    public CategoryGridDTO(String name, int floor, String isle, int bay, Long totalBook) {
        this.name = name;
        this.floor = floor;
        this.isle = isle;
        this.bay = bay;
        this.totalBook = totalBook;
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

    public Long getTotalBook() {
        return totalBook;
    }

    public void setTotalBook(Long totalBook) {
        this.totalBook = totalBook;
    }
}
