package com.higroup.Buda.customDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatePassword {
    private String currentPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String confirmNewPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdatePassword that = (UserUpdatePassword) o;
        return Objects.equals(currentPassword, that.currentPassword) && Objects.equals(newPassword, that.newPassword) && Objects.equals(confirmNewPassword, that.confirmNewPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPassword, newPassword, confirmNewPassword);
    }

    @Override
    public String toString() {
        return "UserUpdatePassword{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmNewPassword='" + confirmNewPassword + '\'' +
                '}';
    }
}
