package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.util.RandomID.RandomIDGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Buy_order", indexes = {
    @Index(columnList = "user_id", name = "buy_order_user_id_index"),
    @Index(columnList = "supplier_id", name = "buy_order_supplier_id_index"),
    @Index(columnList = "text_id", name = "buy_order_text_id_index")
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
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;
    @OneToMany(mappedBy = "buyOrder", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "buy_order - buy_order_item")
    @Fetch(FetchMode.SUBSELECT)
    private Set<BuyOrderItem> buyOrderItems;
    @Column(name = "text_id")
    private String textID = RandomIDGenerator.randomIDString();
    private String description;

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
