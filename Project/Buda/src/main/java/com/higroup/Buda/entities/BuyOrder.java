package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.higroup.Buda.entities.enumeration.Status;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Buy_order", indexes = {
    @Index(columnList = "user_id", name = "buy_order_user_id_index"),
    @Index(columnList = "supplier_id", name = "buy_order_supplier_id_index")
})
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_order_id")
    private Long buyOrderID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "supplier_ID", nullable = true)
    @JsonBackReference
    private Supplier supplier;
    private ZonedDateTime creationTime;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PREPARING;
    private Double totalCost;
    @Column(name = "user_id")
    private Long userID;
    @OneToMany(mappedBy = "buyOrder", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "buy_order - buy_order_item")
    @Fetch(FetchMode.SUBSELECT)
    private Set<BuyOrderItem> buyOrderItems;


    public BuyOrder() {
    }

    public BuyOrder(Long buyOrderID, Supplier supplier, ZonedDateTime creationTime, Status status, Double TotalCost, Long userID, Set<BuyOrderItem> buyOrderItems) {
        this.buyOrderID = buyOrderID;
        this.supplier = supplier;
        this.creationTime = creationTime;
        this.status = status;
        this.totalCost = TotalCost;
        this.userID = userID;
        this.buyOrderItems = buyOrderItems;
    }

    public Long getBuyOrderID() {
        return this.buyOrderID;
    }

    public void setBuyOrderID(Long buyOrderID) {
        this.buyOrderID = buyOrderID;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(Double TotalCost) {
        this.totalCost = TotalCost;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Set<BuyOrderItem> getBuyOrderItems() {
        return this.buyOrderItems;
    }

    public void setBuyOrderItems(Set<BuyOrderItem> buyOrderItems) {
        this.buyOrderItems = buyOrderItems;
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

    public BuyOrder TotalCost(Double TotalCost) {
        setTotalCost(TotalCost);
        return this;
    }

    public BuyOrder userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public BuyOrder buyOrderItems(Set<BuyOrderItem> buyOrderItems) {
        setBuyOrderItems(buyOrderItems);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BuyOrder)) {
            return false;
        }
        BuyOrder buyOrder = (BuyOrder) o;
        return Objects.equals(buyOrderID, buyOrder.buyOrderID)
                && Objects.equals(supplier, buyOrder.supplier)
                && Objects.equals(creationTime, buyOrder.creationTime)
                && Objects.equals(status, buyOrder.status)
                && totalCost == buyOrder.totalCost
                && Objects.equals(userID, buyOrder.userID)
                && Objects.equals(buyOrderItems, buyOrder.buyOrderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyOrderID, supplier, creationTime, status, totalCost, userID, buyOrderItems);
    }

    @Override
    public String toString() {
        return "{" +
            " buyOrderID='" + getBuyOrderID() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", TotalCost='" + getTotalCost() + "'" +
            ", userID='" + getUserID() + "'" +
            ", buyOrderItems='" + getBuyOrderItems() + "'" +
            "}";
    }
    
}
