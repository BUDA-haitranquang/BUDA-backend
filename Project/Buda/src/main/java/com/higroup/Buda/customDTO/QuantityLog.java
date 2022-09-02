package com.higroup.Buda.customDTO;
import javax.validation.constraints.NotNull;

import com.higroup.Buda.entities.enumeration.LeftLogType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class QuantityLog {
    @NotNull
    private Integer amountLeftChange;
    @NotNull 
    private LeftLogType leftLogType;
    private String message;

}
