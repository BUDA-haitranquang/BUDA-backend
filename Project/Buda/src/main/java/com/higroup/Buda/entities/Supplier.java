package com.higroup.Buda.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierID;
    private String email;
    private Long pictureID;
    private String name;
    private String address;
    private String phoneNumber;
    private Long userID;

    public Supplier() {
        super();
    }

    public Supplier(Long supplierID, String email, Long pictureID) {
        super();
        this.supplierID = supplierID;
        this.email = email;
        this.pictureID = pictureID;
    }

    public Long getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPictureID() {
        return this.pictureID;
    }

    public void setPictureID(Long pictureID) {
        this.pictureID = pictureID;
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
        if (!(o instanceof Supplier)) {
            return false;
        }
        Supplier supplier = (Supplier) o;
        return Objects.equals(supplierID, supplier.supplierID) && Objects.equals(email, supplier.email) && Objects.equals(pictureID, supplier.pictureID) && Objects.equals(name, supplier.name) && Objects.equals(address, supplier.address) && Objects.equals(phoneNumber, supplier.phoneNumber) && Objects.equals(userID, supplier.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierID, email, pictureID, name, address, phoneNumber, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " supplierID='" + getSupplierID() + "'" +
            ", email='" + getEmail() + "'" +
            ", pictureID='" + getPictureID() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }

}
