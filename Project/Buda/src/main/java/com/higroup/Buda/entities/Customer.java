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

@Entity
//column nao dinh dang lai ten thi dung ten trong @Column, column nao khong dinh dang lai ten thi dung ten bien
@Table(name = "Customer", indexes = {
    @Index(columnList = "user_id", name = "customer_user_id_index"),
    @Index(columnList = "phoneNumber", name = "customer_phone_number_index")
})
@JsonIgnoreProperties({"sellOrders"})
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerID;
    @Column(columnDefinition = "varchar(50) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    @Column(columnDefinition = "varchar(50) default 'UNKNOWN'")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(columnDefinition = "double default 0.0")
    private double totalSpend;
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
    public Customer() {
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Customer(Long customerID, AgeGroup ageGroup, Gender gender, double totalSpend, Long membershipID,
            String name,/**  String address,*/ String phoneNumber, Long userID, Boolean visible, Set<SellOrder> sellOrders) {
        this.customerID = customerID;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.totalSpend = totalSpend;
        this.membershipID = membershipID;
        this.name = name;
        // this.address = address;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
        this.visible = visible;
        this.sellOrders = sellOrders;
    }

    public Customer(Long customerID, AgeGroup ageGroup, Gender gender, double totalSpend, Long membershipID, String name, /**  String address,*/ String phoneNumber, Long userID, Set<SellOrder> sellOrders) {
        this.customerID = customerID;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.totalSpend = totalSpend;
        this.membershipID = membershipID;
        this.name = name;
        // this.address = address;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
        this.sellOrders = sellOrders;
    }

    public Set<SellOrder> getSellOrders() {
        return this.sellOrders;
    }

    public void setSellOrders(Set<SellOrder> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public Customer sellOrders(Set<SellOrder> sellOrders) {
        setSellOrders(sellOrders);
        return this;
    }

    public Customer(Long customerID, AgeGroup ageGroup, Gender gender, double totalSpend, Long membershipID, String name, /**  String address,*/ String phoneNumber, Long userID) {
        this.customerID = customerID;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.totalSpend = totalSpend;
        this.membershipID = membershipID;
        this.name = name;
        // this.address = address;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
    }

    public Customer customerID(Long customerID) {
        setCustomerID(customerID);
        return this;
    }

    public Customer ageGroup(AgeGroup ageGroup) {
        setAgeGroup(ageGroup);
        return this;
    }

    public Customer gender(Gender gender) {
        setGender(gender);
        return this;
    }

    public Customer totalSpend(double totalSpend) {
        setTotalSpend(totalSpend);
        return this;
    }

    public Customer membershipID(Long membershipID) {
        setMembershipID(membershipID);
        return this;
    }

    public Customer name(String name) {
        setName(name);
        return this;
    }

    // public Customer address(String address) {
    //     setAddress(address);
    //     return this;
    // }

    public Customer phoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public Customer userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public Customer(Long customerID, AgeGroup ageGroup, Gender gender, double totalSpend, Long membershipID) {
        super();
        this.customerID = customerID;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.totalSpend = totalSpend;
        this.membershipID = membershipID;
    }

    public Long getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public AgeGroup getAgeGroup() {
        return this.ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getTotalSpend() {
        return this.totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Long getMembershipID() {
        return this.membershipID;
    }

    public void setMembershipID(Long membershipID) {
        this.membershipID = membershipID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public String getAddress() {
    //     return this.address;
    // }

    // public void setAddress(String address) {
    //     this.address = address;
    // }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID) && Objects.equals(ageGroup, customer.ageGroup) && Objects.equals(gender, customer.gender) && totalSpend == customer.totalSpend && Objects.equals(membershipID, customer.membershipID) && Objects.equals(name, customer.name) /**&& Objects.equals(address, customer.address)*/ && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(userID, customer.userID);
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
