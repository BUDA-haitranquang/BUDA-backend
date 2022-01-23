package com.higroup.Buda.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
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
import com.higroup.Buda.entities.enumeration.AgeGroup;
import com.higroup.Buda.entities.enumeration.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//column nao dinh dang lai ten thi dung ten trong @Column, column nao khong dinh dang lai ten thi dung ten bien
@Table(name = "Customer", indexes = {
    @Index(columnList = "user_id", name = "customer_user_id_index"),
    @Index(columnList = "phoneNumber", name = "customer_phone_number_index")
})
@JsonIgnoreProperties({"sellOrders"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerID;
    @Column(columnDefinition = "varchar(50) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup = AgeGroup.UNKNOWN;
    @Column(columnDefinition = "varchar(50) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.UNKNOWN;
    @Column(columnDefinition = "double default 0.0")
    private double totalSpend = 0.0;
    @Column(name = "membership_id")
    private Long membershipID;
    @Column(length = 50)
    private String name;
    // @Column(length = 200)
    // private String address;
    @Column(length = 15)
    private String phoneNumber;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private Boolean visible = Boolean.TRUE;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "customer - sell_order")
    private Set<SellOrder> sellOrders;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID)
                && Objects.equals(ageGroup, customer.ageGroup)
                && Objects.equals(gender, customer.gender)
                && Objects.equals(totalSpend, customer.totalSpend)
                && Objects.equals(membershipID, customer.membershipID)
                && Objects.equals(name, customer.name)
//                && Objects.equals(address, customer.address)
                && Objects.equals(phoneNumber, customer.phoneNumber)
                && Objects.equals(userID, customer.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }

    @Override
    public String toString() {
        return "{" +
            " customerID='" + getCustomerID() + "'" +
            ", ageGroup='" + getAgeGroup() + "'" +
            ", gender='" + getGender() + "'" +
            ", totalSpend='" + getTotalSpend() + "'" +
            ", membershipID='" + getMembershipID() + "'" +
            ", name='" + getName() + "'" +
            // ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }

}
