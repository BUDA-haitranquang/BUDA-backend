package com.higroup.Buda.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.higroup.Buda.entities.enumeration.AgeGroup;
import com.higroup.Buda.entities.enumeration.Gender;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.util.RandomID.RandomIDGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Sell_order", indexes = {
    @Index(columnList = "user_id", name = "sell_order_user_id_index"),
    @Index(columnList = "customer_id", name = "sell_order_customer_id_index"),
    @Index(columnList = "discount_id", name = "sell_order_discount_id_index"),
    @Index(columnList = "creation_time", name = "sell_order_creation_time_index"),
    @Index(columnList = "text_id", name = "sell_order_text_id_index")
})
//@JsonIgnoreProperties("sellOrderItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SellOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sell_order_id")
    private Long sellOrderID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_ID", nullable = true)
    //@JsonBackReference(value = "customer - sell_order")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "discount_ID", nullable = true)
    @JsonBackReference(value = "discount - sell_order")
    private Discount discount;
    @Column(name = "creation_time")
    private ZonedDateTime creationTime;
    @Column(name = "finish_time")
    private ZonedDateTime finishTime;
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup = AgeGroup.UNKNOWN;
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.UNKNOWN;
    @Column /*(columnDefinition = "Double default 0.0")**/
    private Double actualDiscountCash;
    @Column /*(columnDefinition = "Double default 0.0")**/
    private Double actualDiscountPercentage;
    private Double realCost;
    private Double finalCost;
    @Column(name = "user_id")
    private Long userID;
    // @Column(name = "staff_id")
    // private Long staffID;
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;
    @Column(length = 1000)
    private String customerMessage;
    @Column(length = 200)
    private String address;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PREPARING;
    @OneToMany(mappedBy = "sellOrder")
    @JsonManagedReference(value = "sell_order - sell_order_item")
    @Fetch(FetchMode.SUBSELECT)
    private Set<SellOrderItem> sellOrderItems;
    @Column(name = "text_id")
    private String textID = RandomIDGenerator.randomIDString();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(actualDiscountCash);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(actualDiscountPercentage);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((ageGroup == null) ? 0 : ageGroup.hashCode());
        result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((customerMessage == null) ? 0 : customerMessage.hashCode());
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        temp = Double.doubleToLongBits(finalCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        temp = Double.doubleToLongBits(realCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((sellOrderID == null) ? 0 : sellOrderID.hashCode());
        result = prime * result + ((sellOrderItems == null) ? 0 : sellOrderItems.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "{" +
            " sellOrderID='" + getSellOrderID() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", ageGroup='" + getAgeGroup() + "'" +
            ", gender='" + getGender() + "'" +
            ", actualDiscountCash='" + getActualDiscountCash() + "'" +
            ", actualDiscountPercentage='" + getActualDiscountPercentage() + "'" +
            ", realCost='" + getRealCost() + "'" +
            ", finalCost='" + getFinalCost() + "'" +
            ", userID='" + getUserID() + "'" +
            ", customerMessage='" + getCustomerMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", sellOrderItems='" + getSellOrderItems() + "'" +
            "}";
    }
    
}
