package com.higroup.Buda.entities;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class UserRegister {
    @NotNull
    String username;
    @NotNull
    String phoneNumber;
    @NotNull
    String password;
    @NotNull
    String email;
    @NotNull
    String firstName;
    @NotNull
    String lastName;

    public UserRegister() {
    }

    public UserRegister(String username, String phoneNumber, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRegister username(String username) {
        setUsername(username);
        return this;
    }

    public UserRegister phoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public UserRegister password(String password) {
        setPassword(password);
        return this;
    }

    public UserRegister email(String email) {
        setEmail(email);
        return this;
    }

    public UserRegister firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public UserRegister lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserRegister)) {
            return false;
        }
        UserRegister userRegister = (UserRegister) o;
        return Objects.equals(username, userRegister.username) && Objects.equals(phoneNumber, userRegister.phoneNumber) && Objects.equals(password, userRegister.password) && Objects.equals(email, userRegister.email) && Objects.equals(firstName, userRegister.firstName) && Objects.equals(lastName, userRegister.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, phoneNumber, password, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            "}";
    }

    
}
