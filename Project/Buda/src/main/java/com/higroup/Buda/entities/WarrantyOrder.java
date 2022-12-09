package com.higroup.Buda.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.higroup.Buda.entities.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "warranty_order", indexes = {
    @Index(columnList = "user_id", name = "warranty_order_user_id_index"),
    @Index(columnList = "product_id", name = "warranty_order_product_id_index")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarrantyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warranty_order_id")
    private Long warrantyOrderID;
    @Column(name = "user_id")
    private Long userID;
    @ManyToOne(optional = true)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(optional = true)
    @JoinColumn(name = "sell_order_id")
    private SellOrder sellOrder;
    @ManyToOne(optional = true)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
    private String customerMessage;
    private ZonedDateTime creationTime;
    @Column(name = "status", columnDefinition = "varchar(255) default 'RECEIVING'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.RECEIVING;
   
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((customerMessage == null) ? 0 : customerMessage.hashCode());
//        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        result = prime * result + ((warrantyOrderID == null) ? 0 : warrantyOrderID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WarrantyOrder [creationTime=" + creationTime + ", customerMessage=" + customerMessage + ", status="
//                + status
                + ", userID=" + userID + ", warrantyOrderID=" + warrantyOrderID + "]";
    }
    
    
    
}
