package com.higroup.Buda.customDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterSellOrder {
    private Customer customer;
    private Long discountID;
    private String customer_message;
    private String address;
    @NotNull
    private Status status;
    @NotEmpty
    List<Long> productIDList = new ArrayList<>(); 
    @NotEmpty
    List<Integer> numberProductList = new ArrayList<>(); 
    @NotEmpty
    List<Double> pricePerUnitList = new ArrayList<>();
    
}
