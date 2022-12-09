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
@Table(name = "receipt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Long receiptID;
    private Double totalCost;
    @ManyToOne(optional = true)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;
    private String senderName;
    private String senderContact;
    private ZonedDateTime creationTime;
    @Column(name = "message", length = 1000)
    private String message;
    @Column(name = "user_id")
    private Long userID;
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((receiptID == null) ? 0 : receiptID.hashCode());
        result = prime * result + ((senderContact == null) ? 0 : senderContact.hashCode());
        result = prime * result + ((senderName == null) ? 0 : senderName.hashCode());
        result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        return result;
    }
}
