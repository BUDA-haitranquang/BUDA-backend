package com.higroup.Buda.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.higroup.Buda.entities.enumeration.Status;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Buy_order", indexes = {
    @Index(columnList = "user_id", name = "buy_order_user_id_index"),
    @Index(columnList = "supplier_id", name = "buy_order_supplier_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_order_id")
    private Long buyOrderID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "supplier_ID", nullable = true)
    // @JsonBackReference
    private Supplier supplier;
    @Column(name = "creation_time")
    private ZonedDateTime creationTime;
    @Column(name = "finish_time")
    private ZonedDateTime finishTime;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PREPARING;
    private Double totalCost;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "staff_id")
    private Long staffID;
    @OneToMany(mappedBy = "buyOrder", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "buy_order - buy_order_item")
    @Fetch(FetchMode.SUBSELECT)
    private Set<BuyOrderItem> buyOrderItems;

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
                && Objects.equals(staffID, buyOrder.staffID)
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
            ", staffID='" + getStaffID() + "'" +
            ", buyOrderItems='" + getBuyOrderItems() + "'" +
            "}";
    }
    
}
