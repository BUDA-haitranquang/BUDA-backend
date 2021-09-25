package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "Product_left_log")
public class ProductLeftLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_left_log_id")
    private Long productLeftLogID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "product_ID", nullable = true)
    private Product product;
    private int amountLeftChange;
    private ZonedDateTime creationTime;
    @Column(name = "staff_id")
    private Long staffID;
    @Column(length = 1000)
    private String message;
    @Column(name = "user_id")
    private Long userID;

    public ProductLeftLog() {
    }

    public ProductLeftLog(Long productLeftLogID, Product product, int amountLeftChange, ZonedDateTime creationTime, Long staffID, String message, Long userID) {
        this.productLeftLogID = productLeftLogID;
        this.product = product;
        this.amountLeftChange = amountLeftChange;
        this.creationTime = creationTime;
        this.staffID = staffID;
        this.message = message;
        this.userID = userID;
    }

    public Long getProductLeftLogID() {
        return this.productLeftLogID;
    }

    public void setProductLeftLogID(Long productLeftLogID) {
        this.productLeftLogID = productLeftLogID;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmountLeftChange() {
        return this.amountLeftChange;
    }

    public void setAmountLeftChange(int amountLeftChange) {
        this.amountLeftChange = amountLeftChange;
    }

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getStaffID() {
        return this.staffID;
    }

    public void setStaffID(Long staffID) {
        this.staffID = staffID;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public ProductLeftLog productLeftLogID(Long productLeftLogID) {
        setProductLeftLogID(productLeftLogID);
        return this;
    }

    public ProductLeftLog product(Product product) {
        setProduct(product);
        return this;
    }

    public ProductLeftLog amountLeftChange(int amountLeftChange) {
        setAmountLeftChange(amountLeftChange);
        return this;
    }

    public ProductLeftLog creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    public ProductLeftLog staffID(Long staffID) {
        setStaffID(staffID);
        return this;
    }

    public ProductLeftLog message(String message) {
        setMessage(message);
        return this;
    }

    public ProductLeftLog userID(Long userID) {
        setUserID(userID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductLeftLog)) {
            return false;
        }
        ProductLeftLog productLeftLog = (ProductLeftLog) o;
        return Objects.equals(productLeftLogID, productLeftLog.productLeftLogID) && Objects.equals(product, productLeftLog.product) && amountLeftChange == productLeftLog.amountLeftChange && Objects.equals(creationTime, productLeftLog.creationTime) && Objects.equals(staffID, productLeftLog.staffID) && Objects.equals(message, productLeftLog.message) && Objects.equals(userID, productLeftLog.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productLeftLogID, product, amountLeftChange, creationTime, staffID, message, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " productLeftLogID='" + getProductLeftLogID() + "'" +
            ", product='" + getProduct() + "'" +
            ", amountLeftChange='" + getAmountLeftChange() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", staffID='" + getStaffID() + "'" +
            ", message='" + getMessage() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }

}
