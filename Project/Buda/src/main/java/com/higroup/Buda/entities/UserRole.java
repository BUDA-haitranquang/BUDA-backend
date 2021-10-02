package com.higroup.Buda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// @Entity
// @Table(name = "User_Role", //
//         uniqueConstraints = { //
//                 @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "User_Id", "Role_Id" }) })
public class UserRole {

    // @Id
    // @GeneratedValue
    // @Column(name = "Id", nullable = false)
    // private Long id;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "User_Id", nullable = false)
    // private User user;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "Role_Id", nullable = false)
    // private AppRole appRole;

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public User getUser() {
    //     return user;
    // }

    // public void setUser(User User) {
    //     this.user = User;
    // }

    // public AppRole getAppRole() {
    //     return appRole;
    // }

    // public void setAppRole(AppRole appRole) {
    //     this.appRole = appRole;
    // }
    
}