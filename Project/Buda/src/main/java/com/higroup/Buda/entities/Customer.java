package com.higroup.Buda.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;
    private AgeGroup ageGroup;
    private Gender gender;
    private double totalSpend;
    private Long membershipID;
    private String name;
    private String address;
    private String phoneNumber;
    private Long userID;

    public Customer() {
        super();
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
        return Objects.equals(customerID, customer.customerID) && Objects.equals(ageGroup, customer.ageGroup) && Objects.equals(gender, customer.gender) && totalSpend == customer.totalSpend && Objects.equals(membershipID, customer.membershipID) && Objects.equals(name, customer.name) && Objects.equals(address, customer.address) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(userID, customer.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, ageGroup, gender, totalSpend, membershipID, name, address, phoneNumber, userID);
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
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }

}
