package com.netcracker.denisik.dto;

public abstract class BaseDTO {
    private long id;

    public BaseDTO() {
    }

    public BaseDTO(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
