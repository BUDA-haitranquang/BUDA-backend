package com.higroup.Buda.customDTO;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
