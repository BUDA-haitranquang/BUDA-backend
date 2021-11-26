package com.higroup.Buda.entities;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.higroup.Buda.util.SHA_256_Encode;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "User", indexes = {
    @Index(columnList = "email", name = "user_email_index"),
    @Index(columnList = "phoneNumber", name = "user_phone_number_index"),
    @Index(columnList = "userName", name = "user_user_name_index")
})
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
    @Column(name = "picture_id")
    private Long pictureID;
    @OneToMany(mappedBy = "user",
    fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Purchase> purchases; 
    public Set<Purchase> getPurchases() {
        return this.purchases;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id"), 
               inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles = new ArrayList<Role>();

    public User(Long userID, String userUUID, String userName, String password, String email, String phoneNumber, String lastName, String firstName, Long pictureID, Set<Purchase> purchases) {
        this.userID = userID;
        this.userUUID = userUUID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.pictureID = pictureID;
        this.purchases = purchases;
    }

    public User(UserRegister userRegister)
    {
        this.email = userRegister.getEmail();
        this.userName = userRegister.getUsername();
        this.phoneNumber = userRegister.getPhoneNumber();
        this.password = userRegister.getPassword();
        this.firstName = userRegister.getFirstName();
        this.lastName = userRegister.getLastName();
    }
    public User userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public User userUUID(String userUUID) {
        setUserUUID(userUUID);
        return this;
    }

    public User userName(String userName) {
        setUserName(userName);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User phoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public User lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public User firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public User pictureID(Long pictureID) {
        setPictureID(pictureID);
        return this;
    }

    public User purchases(Set<Purchase> purchases) {
        setPurchases(purchases);
        return this;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }
    
    public User() {
    }

    public String getUserUUID() {
        return this.userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long id) {
        this.userID = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        if (userName == null)
        {
            return;
        }
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password == null)
        {
            return;
        }
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email == null)
        {
            return;
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null)
        {
            return;
        }
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null)
        {
            return;
        }
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null)
        {
            return;
        }
        this.firstName = firstName;
    }

    public Long getPictureID() {
        return this.pictureID;
    }

    public void setPictureID(Long pictureID) {
        this.pictureID = pictureID;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

}
