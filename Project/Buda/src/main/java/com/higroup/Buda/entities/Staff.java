package com.higroup.Buda.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Staff", indexes = {
    @Index(columnList = "user_id", name = "staff_user_id_index")
})
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long staffID;
    @Column(length = 30)
    private String name;
    @Column(length = 15)
    private String phoneNumber;
    private String password;
    private String address;
    @Column(length = 50)
    private String email;

    @Column(length=30)
    private String account;
    @Column(name = "user_id")
    private Long userID;  
    @Enumerated(EnumType.STRING)
    private StaffPosition staffPosition = StaffPosition.BASIC;
    @Column(columnDefinition = "varchar(36) default (uuid())", name = "staff_uuid")
    private String staffUUID;
    @Column(columnDefinition = "Double default 0.0")
    private Double salary;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "staff_role",
               joinColumns = @JoinColumn(name = "staff_id"), 
               inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles = new ArrayList<Role>();

    public String getStaffUUID() {
        return this.staffUUID;
    }

    public void setStaffUUID(String staffUUID) {
        this.staffUUID = staffUUID;
    }

    public Staff() {
    }

    public Staff(Long staffID, String name, String phoneNumber, String password, String address, Long userID, 
                 StaffPosition staffPosition, String loginID, Double salary, String account, String email) 
    {
        this.staffID = staffID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.userID = userID;
        this.staffPosition = staffPosition;
        this.staffUUID = loginID;
        this.salary = salary;
        this.account = account;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
        return this.staffUUID;
    }

    public void setLoginID(String loginID) {
        this.staffUUID = loginID;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
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

    public Staff salary(Double salary) {
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
        return Objects.equals(staffID, staff.staffID) && Objects.equals(name, staff.name) && Objects.equals(phoneNumber, staff.phoneNumber) && Objects.equals(password, staff.password) && Objects.equals(address, staff.address) && Objects.equals(userID, staff.userID) && Objects.equals(staffPosition, staff.staffPosition) && Objects.equals(staffUUID, staff.staffUUID) && salary == staff.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffID, name, phoneNumber, password, address, userID, staffPosition, staffUUID, salary);
    }

    @Override
    public String toString() {
        return "{" +
            " staffID='" + getStaffID() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", account='" + getAccount() + "'" +
            ", password='" + getPassword() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", userID='" + getUserID() + "'" +
            ", staffPosition='" + getStaffPosition() + "'" +
            ", staffUUID='" + getStaffUUID() + "'" +
            ", salary='" + getSalary() + "'" +
            "}";
    }

    // role 
    // add role
    public void addRole(Role ... roles){
        for(Role role: roles){
            if(!this.roles.contains(role)){
                this.roles.add(role);
            }
        }
    }
    
    // remove role 
    public void removeRole(Role ... roles){
        for(Role role: roles){
            if(this.roles.contains(role)){
                this.roles.remove(role);
            }
        }
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

}
