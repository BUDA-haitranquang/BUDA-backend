package com.higroup.Buda.entities;

import java.util.Objects;

public class UserLogin {
    private String email;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLogin email(String email) {
        setEmail(email);
        return this;
    }

    public UserLogin password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserLogin)) {
            return false;
        }
        UserLogin userLogin = (UserLogin) o;
        return Objects.equals(email, userLogin.email) && Objects.equals(password, userLogin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    
}
