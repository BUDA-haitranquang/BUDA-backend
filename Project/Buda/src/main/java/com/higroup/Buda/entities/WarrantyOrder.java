package com.higroup.Buda.entities;

import java.time.ZonedDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "warranty_order", indexes = {
    @Index(columnList = "user_id", name = "warranty_order_user_id_index"),
    @Index(columnList = "product_id", name = "warranty_order_product_id_index")
})
public class WarrantyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warranty_order_id")
    private Long warrantyOrderID;
    @Column(name = "user_id")
    private Long userID;
    @ManyToOne(optional = true)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(optional = true)
    @JoinColumn(name = "sell_order_id")
    private SellOrder sellOrder;
    @ManyToOne(optional = true)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String customerMessage;
    private ZonedDateTime creationTime;
    private Status status = Status.RECEIVING;
    public Long getWarrantyOrderID() {
        return warrantyOrderID;
    }
    public void setWarrantyOrderID(Long warrantyOrderID) {
        this.warrantyOrderID = warrantyOrderID;
    }
    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public SellOrder getSellOrder() {
        return sellOrder;
    }
    public void setSellOrder(SellOrder sellOrder) {
        this.sellOrder = sellOrder;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getCustomerMessage() {
        return customerMessage;
    }
    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }
    public ZonedDateTime getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public WarrantyOrder() {
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((customerMessage == null) ? 0 : customerMessage.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        result = prime * result + ((warrantyOrderID == null) ? 0 : warrantyOrderID.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WarrantyOrder other = (WarrantyOrder) obj;
        if (creationTime == null) {
            if (other.creationTime != null)
                return false;
        } else if (!creationTime.equals(other.creationTime))
            return false;
        if (customerMessage == null) {
            if (other.customerMessage != null)
                return false;
        } else if (!customerMessage.equals(other.customerMessage))
            return false;
        if (status != other.status)
            return false;
        if (userID == null) {
            if (other.userID != null)
                return false;
        } else if (!userID.equals(other.userID))
            return false;
        if (warrantyOrderID == null) {
            if (other.warrantyOrderID != null)
                return false;
        } else if (!warrantyOrderID.equals(other.warrantyOrderID))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "WarrantyOrder [creationTime=" + creationTime + ", customerMessage=" + customerMessage + ", status="
                + status + ", userID=" + userID + ", warrantyOrderID=" + warrantyOrderID + "]";
    }
    public WarrantyOrder(Long warrantyOrderID, Long userID, Product product, SellOrder sellOrder, Customer customer,
            String customerMessage, ZonedDateTime creationTime, Status status) {
        this.warrantyOrderID = warrantyOrderID;
        this.userID = userID;
        this.product = product;
        this.sellOrder = sellOrder;
        this.customer = customer;
        this.customerMessage = customerMessage;
        this.creationTime = creationTime;
        this.status = status;
    }
    
    
}
