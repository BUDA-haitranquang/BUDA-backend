package com.higroup.Buda.customDTO;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class StaffLogin {
    @NotNull
    private String account;
    @NotNull
    private String password;

    public StaffLogin() {
    }

    public StaffLogin(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StaffLogin account(String acouunt) {
        setAccount(acouunt);
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
        return Objects.equals(account, staffLogin.account) && Objects.equals(password, staffLogin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, password);
    }

    @Override
    public String toString() {
        return "{" +
            " uuid='" + getAccount() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    
}
