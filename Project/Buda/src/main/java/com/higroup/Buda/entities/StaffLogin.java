package com.higroup.Buda.entities;

import java.util.Objects;

public class StaffLogin {
    private String uuid;
    private String password;

    public StaffLogin() {
    }

    public StaffLogin(String uuid, String password) {
        this.uuid = uuid;
        this.password = password;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StaffLogin uuid(String uuid) {
        setUuid(uuid);
        return this;
    }

    public StaffLogin password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StaffLogin)) {
            return false;
        }
        StaffLogin staffLogin = (StaffLogin) o;
        return Objects.equals(uuid, staffLogin.uuid) && Objects.equals(password, staffLogin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, password);
    }

    @Override
    public String toString() {
        return "{" +
            " uuid='" + getUuid() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    
}
