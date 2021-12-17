package com.higroup.Buda.customDTO;

import javax.validation.constraints.NotNull;

public class RegisterSellOrderItem {
    @NotNull
    private Long productID;
    @NotNull
    private Long sellOrderID;
    @NotNull
    private Integer quantity;
    public RegisterSellOrderItem(Long productID, Long sellOrderID, Integer quantity) {
        this.productID = productID;
        this.sellOrderID = sellOrderID;
        this.quantity = quantity;
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
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
