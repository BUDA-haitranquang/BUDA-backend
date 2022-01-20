package com.higroup.Buda.api.business.sell.neworder;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellOrderDTO {
    private Customer customer;
    private Long discountID;
    private String customerMessage;
    private String address;
    @NotNull
    private Status status;
    @NotEmpty
    private List<SellOrderItemDTO> sellOrderItemDTOs;
}
