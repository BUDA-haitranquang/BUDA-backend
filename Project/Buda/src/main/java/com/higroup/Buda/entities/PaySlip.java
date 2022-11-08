package com.higroup.Buda.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "pay_slip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_slip_id")
    private Long paySlipID;
    @ManyToOne(optional = true)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "supplier_id", nullable = true)
    private Supplier supplier;
    private String receiverName;
    private String receiverContact;
    private ZonedDateTime creationTime;
    @Column(length = 1000)
    private String message;
    private Double totalCost;
    @Column(name = "user_id")
    private Long userID;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((paySlipID == null) ? 0 : paySlipID.hashCode());
        result = prime * result + ((receiverContact == null) ? 0 : receiverContact.hashCode());
        result = prime * result + ((receiverName == null) ? 0 : receiverName.hashCode());
        result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        return result;
    }
    
}
