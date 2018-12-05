package com.netcracker.denisik.entities;

public class AdminEntity extends UserEntity {
    private boolean status;

    public AdminEntity() {
    }

    public AdminEntity(UserEntity userEntity, boolean status) {
        super(userEntity);
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminEntity)) return false;
        if (!super.equals(o)) return false;

        AdminEntity that = (AdminEntity) o;

        if (getRole() != that.getRole()) return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if(getLogin() != null ? getLogin().equals(that.getLogin()) : that.getLogin() == null)
            return false;
        return getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getStatus() ? 1 : 0);
        return result;
    }
}
