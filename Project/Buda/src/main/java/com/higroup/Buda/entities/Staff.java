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

import com.higroup.Buda.entities.enumeration.StaffPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Staff", indexes = {
    @Index(columnList = "user_id", name = "staff_user_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
               inverseJoinColumns = @JoinColumn(name = "role_id"),
               indexes = {
                   @Index(name = "staff_role_staff_id", columnList = "staff_id")
               }
    )
    private Collection<Role> roles = new ArrayList<Role>();

    @Column(columnDefinition = "boolean default true")
    private Boolean enabled = Boolean.TRUE;

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
