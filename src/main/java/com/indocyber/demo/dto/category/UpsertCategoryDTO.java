package com.indocyber.demo.dto.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpsertCategoryDTO {

    @NotBlank
    private String name;

    @NotNull
    private int floor;

    @NotBlank
    private String isle;

    @NotNull
    private int bay;

    public UpsertCategoryDTO(){}

    public UpsertCategoryDTO(String name, int floor, String isle, int bay) {
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
}
