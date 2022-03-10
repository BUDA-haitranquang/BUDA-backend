package com.higroup.Buda.api.business.buy.neworder;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyOrderDTO {
    private Supplier supplier;
    private String address;
    @NotNull
    private Status status;
    @NotEmpty
    private List<BuyOrderItemDTO> buyOrderItemDTOs;
    
}
