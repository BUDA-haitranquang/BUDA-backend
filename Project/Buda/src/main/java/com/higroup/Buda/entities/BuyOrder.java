package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Buy_order")
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyOrderID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "supplierID", nullable = true)
    @JsonBackReference
    private Supplier supplier;
    private ZonedDateTime creationTime;
    private Status status;
    private double TotalCost;
    private Long userID;
    @OneToMany(mappedBy = "buyOrder", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<BuyOrderItem> buyOrderItems;

    public BuyOrder(Long buyOrderID, Supplier supplier, ZonedDateTime creationTime, Status status, double totalCost, Long userID, Set<BuyOrderItem> buyOrderItems) {
        this.buyOrderID = buyOrderID;
        this.supplier = supplier;
        this.creationTime = creationTime;
        this.status = status;
        TotalCost = totalCost;
        this.userID = userID;
        this.buyOrderItems = buyOrderItems;
    }

    public BuyOrder() {

    }

    public Set<BuyOrderItem> getBuyOrderItems() {
        return buyOrderItems;
    }

    public void setBuyOrderItems(Set<BuyOrderItem> buyOrderItems) {
        this.buyOrderItems = buyOrderItems;
    }

    public BuyOrder buyOrderItems(Set<BuyOrderItem> buyOrderItems) {
        setBuyOrderItems(buyOrderItems);
        return this;
    }

    public Long getBuyOrderID() {
        return buyOrderID;
    }

    public void setBuyOrderID(Long buyOrderID) {
        this.buyOrderID = buyOrderID;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public BuyOrder buyOrderID(Long buyOrderID) {
        setBuyOrderID(buyOrderID);
        return this;
    }

    public BuyOrder supplier(Supplier supplier) {
        setSupplier(supplier);
        return this;
    }

    public BuyOrder creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    public BuyOrder status(Status status) {
        setStatus(status);
        return this;
    }

    public BuyOrder totalCost(double totalCost) {
        setTotalCost(totalCost);
        return this;
    }

    public BuyOrder userID(Long userID) {
        setUserID(userID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyOrder buyOrder = (BuyOrder) o;
        return Double.compare(buyOrder.TotalCost, TotalCost) == 0 && Objects.equals(buyOrderID, buyOrder.buyOrderID) && Objects.equals(supplier, buyOrder.supplier) && Objects.equals(creationTime, buyOrder.creationTime) && status == buyOrder.status && Objects.equals(userID, buyOrder.userID) && Objects.equals(buyOrderItems, buyOrder.buyOrderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyOrderID, supplier, creationTime, status, TotalCost, userID, buyOrderItems);
    }

    @Override
    public String toString() {
        return "BuyOrder{" +
                "buyOrderID=" + buyOrderID +
                ", supplier=" + supplier +
                ", creationTime=" + creationTime +
                ", status=" + status +
                ", TotalCost=" + TotalCost +
                ", userID=" + userID +
                ", buyOrderItems=" + buyOrderItems +
                '}';
    }
}
