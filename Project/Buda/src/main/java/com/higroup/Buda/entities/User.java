package com.higroup.Buda.entities;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.ArrayList;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.higroup.Buda.customDTO.UserRegister;
import com.higroup.Buda.entities.enumeration.PlanType;
import com.higroup.Buda.util.SHA_256_Encode;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "User", indexes = {
    @Index(columnList = "email", name = "user_email_index"),
    @Index(columnList = "phoneNumber", name = "user_phone_number_index"),
    @Index(columnList = "userName", name = "user_user_name_index")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("purchases")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_ID")
    private Long userID;
    @Column(columnDefinition = "varchar(50) default (uuid())", name = "user_uuid")
    private String userUUID;
    @Column(length = 30)
    private String userName;
    @Column(length = 128)
    private String password;
    @Column(length = 50)
    private String email;
    @Column(length = 15)
    private String phoneNumber;
    @Column(length = 20)
    private String lastName;
    @Column(length = 20)
    private String firstName;
    @Column(name = "enabled")
    private Boolean enabled = false;
    @Column(name = "plan_type", columnDefinition = "varchar(255) default 'BASIC'")
    @Enumerated(EnumType.STRING)
    private PlanType planType = PlanType.BASIC;
    @Column(name = "picture_id")
    private Long pictureID;
    @OneToMany(mappedBy = "user",
    fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Purchase> purchases; 

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id"), 
               inverseJoinColumns = @JoinColumn(name = "role_id"),
               indexes = {
                   @Index(name = "user_role_user_id", columnList = "user_id")
               }
    )
    private Collection<Role> roles = new ArrayList<Role>();


    public User(UserRegister userRegister)
    {
        this.email = userRegister.getEmail();
        this.userName = userRegister.getUsername();
        this.phoneNumber = userRegister.getPhoneNumber();
        this.password = userRegister.getPassword();
        this.firstName = userRegister.getFirstName();
        this.lastName = userRegister.getLastName();
    }
    
    public Boolean isEnabled() {
        return enabled;
    }


    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
                + ", phoneNumber=" + phoneNumber + ", pictureID=" + pictureID + ", roles="
                + roles + ", userID=" + userID + ", userName=" + userName + ", userUUID=" + userUUID + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userID, user.userID) && Objects.equals(userUUID, user.userUUID) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(pictureID, user.pictureID) && Objects.equals(purchases, user.purchases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
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

}
