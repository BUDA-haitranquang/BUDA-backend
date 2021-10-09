package com.higroup.Buda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleID;

    @Column(length = 10, unique = true)
    private String name;
    
    public Role(){};

    public Role(String name){
        this.name = name;
    }

    public long getRoleID() {
        return roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role [name=" + name + ", roleID=" + roleID + "]";
    }

    
}

