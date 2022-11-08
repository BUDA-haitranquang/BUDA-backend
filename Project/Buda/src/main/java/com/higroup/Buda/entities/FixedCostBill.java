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
@Table(name = "Fixed_cost_bill", indexes = {
    @Index(columnList = "user_id", name = "fixed_cost_bill_user_id_index"),
    @Index(columnList = "fixed_cost_id", name = "fixed_cost_bill_fixed_cost_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FixedCostBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fixed_cost_bill_id")
    private Long fixedCostBillID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fixed_Cost_ID", nullable = true)
    @JsonBackReference(value = "fixed_cost - fixed_cost_bill")
    private FixedCost fixedCost;
    @Column(name = "user_id")
    private Long userID;
    private Double totalSpend;
    @Column(length = 1000)
    private String message;
    private ZonedDateTime creationTime;
    private ZonedDateTime dueTime;
    @Column(name = "closed_time")
    private ZonedDateTime closedTime;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PREPARING;

    @Override
    public int hashCode() {
//        return Objects.hash(fixedCostBillID, fixedCost, userID, totalSpend, message, creationTime, dueTime, status);
        return 1;
    }

    @Override
    public String toString() {
        return "{" +
            " fixedCostBillID='" + getFixedCostBillID() + "'" +
//            ", fixedCost='" + getFixedCost() + "'" +
            ", userID='" + getUserID() + "'" +
            ", totalSpend='" + getTotalSpend() + "'" +
            ", message='" + getMessage() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", dueTime='" + getDueTime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
   
}
