package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.higroup.Buda.entities.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Purchase", indexes = {
    @Index(columnList = "user_id", name = "purchase_user_id_index"),
    @Index(columnList = "plan_id", name = "purchase_plan_id_index")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long purchaseID;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    @JsonBackReference
    private Plan plan;
    private ZonedDateTime creationTime;
    private ZonedDateTime expiryDate;
    @Column(length = 1000)
    private String message;
    @Column(columnDefinition = "Double default 0.0")
    private Double totalCost;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PREPARING;
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return Objects.equals(purchaseID, purchase.purchaseID) && Objects.equals(user, purchase.user) && Objects.equals(plan, purchase.plan) && Objects.equals(creationTime, purchase.creationTime) && Objects.equals(expiryDate, purchase.expiryDate) && Objects.equals(message, purchase.message) && totalCost == purchase.totalCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseID);
    }

    @Override
    public String toString() {
        return "{" +
            " purchaseID='" + getPurchaseID() + "'" +
            ", user='" + getUser() + "'" +
            ", plan='" + getPlan() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", message='" + getMessage() + "'" +
            ", totalCost='" + getTotalCost() + "'" +
            "}";
    }
    
}
