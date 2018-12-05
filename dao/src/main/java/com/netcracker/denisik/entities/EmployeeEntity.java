package com.netcracker.denisik.entities;

public class EmployeeEntity extends UserEntity {
    private String name;

    public EmployeeEntity() {
    }

    public EmployeeEntity(UserEntity userEntity, String name) {
        super(userEntity);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity)) return false;
        if (!super.equals(o)) return false;

        EmployeeEntity that = (EmployeeEntity) o;
        if (getRole() != that.getRole()) return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if(getLogin() != null ? getLogin().equals(that.getLogin()) : that.getLogin() == null)
            return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
