package com.netcracker.denisik.dto;

public abstract class BaseDTO {
    private int id;

    public BaseDTO(){}

    public BaseDTO(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
