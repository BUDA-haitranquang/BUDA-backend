package com.higroup.Buda.customDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.enumeration.Status;

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

    public RegisterSellOrder(){};
    
    public RegisterSellOrder(Customer customer, Long discountID, String customer_message, String address,
            @NotNull Status status, @NotEmpty List<Long> productIDList, @NotEmpty List<Integer> numberProductList) {
        this.customer = customer;
        this.discountID = discountID;
        this.customer_message = customer_message;
        this.address = address;
        this.status = status;
        this.productIDList = productIDList;
        this.numberProductList = numberProductList;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Long getDiscountID() {
        return discountID;
    }
    public void setDiscountID(Long discountID) {
        this.discountID = discountID;
    }
    
    public List<Long> getProductIDList() {
        return productIDList;
    }

    public void setProductIDList(List<Long> productIDList) {
        this.productIDList = productIDList;
    }

    public List<Integer> getNumberProductList() {
        return numberProductList;
    }

    public void setNumberProductList(List<Integer> numberProductList) {
        this.numberProductList = numberProductList;
    }

    public String getCustomer_message() {
        return customer_message;
    }
    public void setCustomer_message(String customer_message) {
        this.customer_message = customer_message;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    
}
