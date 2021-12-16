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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Discount", indexes = {
    @Index(columnList = "user_id", name = "discount_user_id_index")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"sellOrders", "membershipTypes"})
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long discountID;
    @Column(length = 200)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(columnDefinition = "Double default 0.0")
    private Double cash;
    @Column(columnDefinition = "Double default 0.0")
    private Double percentage;
    @Column(columnDefinition = "Double default 0.0")
    private Double cashLimit;
    @Column(columnDefinition = "int default 0")
    private Integer orderCount;
    private ZonedDateTime expiryTime;
    private ZonedDateTime createdTime;
    @Column(name = "user_id")
    private Long userID;
    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "discount - sell_order")
    private Set<SellOrder> sellOrders;
    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "discount - membership_type")
    private Set<MembershipType> membershipTypes;
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Discount)) {
            return false;
        }
        Discount discount = (Discount) o;
        return Objects.equals(discountID, discount.discountID) && Objects.equals(name, discount.name) && Objects.equals(description, discount.description) && cash == discount.cash && percentage == discount.percentage && cashLimit == discount.cashLimit && orderCount == discount.orderCount && Objects.equals(expiryTime, discount.expiryTime) && Objects.equals(createdTime, discount.createdTime) && Objects.equals(userID, discount.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountID);
    }

    @Override
    public String toString() {
        return "{" +
            " discountID='" + getDiscountID() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", cash='" + getCash() + "'" +
            ", percentage='" + getPercentage() + "'" +
            ", cashLimit='" + getCashLimit() + "'" +
            ", orderCount='" + getOrderCount() + "'" +
            ", expiryTime='" + getExpiryTime() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", userID='" + getUserID() + "'" +
            //", sellOrders='" + getSellOrders() + "'" +
            "}";
    }

}
