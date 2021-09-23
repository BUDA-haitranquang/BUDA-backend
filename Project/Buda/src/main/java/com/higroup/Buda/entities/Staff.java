package com.higroup.Buda.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffID;
    @Column(length = 30)
    private String name;
    @Column(length = 15)
    private String phoneNumber;
    private String password;
    private String address;
    private Long userID;  
    private StaffPosition staffPosition;
    @Column(columnDefinition = "varchar(36) default (uuid())")
    private String loginID;
    private double salary;

    public Staff() {
    }

    public Staff(Long staffID, String name, String phoneNumber, String password, String address, Long userID, StaffPosition staffPosition, String loginID, double salary) {
        this.staffID = staffID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.userID = userID;
        this.staffPosition = staffPosition;
        this.loginID = loginID;
        this.salary = salary;
    }

    public Long getStaffID() {
        return this.staffID;
    }

    public void setStaffID(Long staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public StaffPosition getStaffPosition() {
        return this.staffPosition;
    }

    public void setStaffPosition(StaffPosition staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getLoginID() {
        return this.loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Staff staffID(Long staffID) {
        setStaffID(staffID);
        return this;
    }

    public Staff name(String name) {
        setName(name);
        return this;
    }

    public Staff phoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public Staff password(String password) {
        setPassword(password);
        return this;
    }

    public Staff address(String address) {
        setAddress(address);
        return this;
    }

    public Staff userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public Staff staffPosition(StaffPosition staffPosition) {
        setStaffPosition(staffPosition);
        return this;
    }

    public Staff loginID(String loginID) {
        setLoginID(loginID);
        return this;
    }

    public Staff salary(double salary) {
        setSalary(salary);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Staff)) {
            return false;
        }
        Staff staff = (Staff) o;
        return Objects.equals(staffID, staff.staffID) && Objects.equals(name, staff.name) && Objects.equals(phoneNumber, staff.phoneNumber) && Objects.equals(password, staff.password) && Objects.equals(address, staff.address) && Objects.equals(userID, staff.userID) && Objects.equals(staffPosition, staff.staffPosition) && Objects.equals(loginID, staff.loginID) && salary == staff.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffID, name, phoneNumber, password, address, userID, staffPosition, loginID, salary);
    }

    @Override
    public String toString() {
        return "{" +
            " staffID='" + getStaffID() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", password='" + getPassword() + "'" +
            ", address='" + getAddress() + "'" +
            ", userID='" + getUserID() + "'" +
            ", staffPosition='" + getStaffPosition() + "'" +
            ", loginID='" + getLoginID() + "'" +
            ", salary='" + getSalary() + "'" +
            "}";
    }

}
