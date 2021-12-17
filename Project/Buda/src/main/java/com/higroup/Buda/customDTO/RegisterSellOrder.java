package com.higroup.Buda.customDTO;

import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Status;

public class RegisterSellOrder {
    private Customer customer;
    private Long discountID;
    private String customer_message;
    private String address;
    @NotNull
    private Status status;
    @NotEmpty
    Map<Long, Integer> products; 

    public RegisterSellOrder(){};
    public RegisterSellOrder(Customer customer, Long discountID, String customer_message, Status status, Map<Long, Integer> products, String address) 
    {
        this.customer = customer;
        this.discountID = discountID;
        this.customer_message = customer_message;
        this.status = status;
        this.products = products;
        this.address = address;
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
    
    
    public Map<Long, Integer> getProducts() {
        return products;
    }
    public void setProducts(Map<Long, Integer> products) {
        this.products = products;
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
