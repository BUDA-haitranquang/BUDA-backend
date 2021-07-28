package com.higroup.Buda.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planID")
    Long planID;
    @OneToMany(mappedBy = "plan",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Purchase> purchases;
    private String name;
    private double Price;
    private int duration;
    private Long pictureID;

    public Plan() {
    }

    public Plan(Long planID, Set<Purchase> purchases, String name, double Price, int duration, Long pictureID) {
        this.planID = planID;
        this.purchases = purchases;
        this.name = name;
        this.Price = Price;
        this.duration = duration;
        this.pictureID = pictureID;
    }

    public Long getPlanID() {
        return this.planID;
    }

    public void setPlanID(Long planID) {
        this.planID = planID;
    }

    public Set<Purchase> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getPictureID() {
        return this.pictureID;
    }

    public void setPictureID(Long pictureID) {
        this.pictureID = pictureID;
    }
    
}
