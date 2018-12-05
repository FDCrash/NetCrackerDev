package com.netcracker.denisik.entities;

public abstract class BaseEntity {
    private int id;

    public BaseEntity() {
    }

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public void setId(int id) {
        this.id = id;
    }
}
