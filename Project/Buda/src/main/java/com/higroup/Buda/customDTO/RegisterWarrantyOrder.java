package com.higroup.Buda.customDTO;

import javax.validation.constraints.NotNull;

public class RegisterWarrantyOrder {
    @NotNull
    private Long productID;
    @NotNull
    private Long sellOrderID;
    @NotNull
    private Long customerID;
    private String customerMessage;

    public RegisterWarrantyOrder() {
    }

    public RegisterWarrantyOrder(Long productID, Long sellOrderID, Long customerID, String customerMessage) {
        this.productID = productID;
        this.sellOrderID = sellOrderID;
        this.customerID = customerID;
        this.customerMessage = customerMessage;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getSellOrderID() {
        return sellOrderID;
    }

    public void setSellOrderID(Long sellOrderID) {
        this.sellOrderID = sellOrderID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }
}
