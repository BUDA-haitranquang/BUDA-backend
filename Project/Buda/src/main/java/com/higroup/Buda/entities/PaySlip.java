package com.higroup.Buda.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Supplier supplier;
    private String receiverName;
    private String receiverContact;
    private ZonedDateTime creationTime;
    private Long totalCost;
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaySlip other = (PaySlip) obj;
        if (creationTime == null) {
            if (other.creationTime != null)
                return false;
        } else if (!creationTime.equals(other.creationTime))
            return false;
        if (paySlipID == null) {
            if (other.paySlipID != null)
                return false;
        } else if (!paySlipID.equals(other.paySlipID))
            return false;
        if (receiverContact == null) {
            if (other.receiverContact != null)
                return false;
        } else if (!receiverContact.equals(other.receiverContact))
            return false;
        if (receiverName == null) {
            if (other.receiverName != null)
                return false;
        } else if (!receiverName.equals(other.receiverName))
            return false;
        if (totalCost == null) {
            if (other.totalCost != null)
                return false;
        } else if (!totalCost.equals(other.totalCost))
            return false;
        if (userID == null) {
            if (other.userID != null)
                return false;
        } else if (!userID.equals(other.userID))
            return false;
        return true;
    }
   
    
}
