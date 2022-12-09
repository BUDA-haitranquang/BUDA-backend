package com.higroup.Buda.api.user.updateinfo.email;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleEmailDTO {
    @NotNull(message = "email not null")
    private String email;
}
