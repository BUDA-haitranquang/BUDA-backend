package com.higroup.Buda.entities;

import java.util.Objects;
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
    @Column(length = 50)
    private String name;
    private double Price;
    private int duration;
    private Long pictureID;
    @Column(length = 200)
    private String description;

    public Plan(Long planID, Set<Purchase> purchases, String name, double Price, int duration, Long pictureID, String description) {
        this.planID = planID;
        this.purchases = purchases;
        this.name = name;
        this.Price = Price;
        this.duration = duration;
        this.pictureID = pictureID;
        this.description = description;
    }

    public Plan planID(Long planID) {
        setPlanID(planID);
        return this;
    }

    public Plan purchases(Set<Purchase> purchases) {
        setPurchases(purchases);
        return this;
    }

    public Plan name(String name) {
        setName(name);
        return this;
    }

    public Plan Price(double Price) {
        setPrice(Price);
        return this;
    }

    public Plan duration(int duration) {
        setDuration(duration);
        return this;
    }

    public Plan pictureID(Long pictureID) {
        setPictureID(pictureID);
        return this;
    }

    public Plan description(String description) {
        setDescription(description);
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Plan)) {
            return false;
        }
        Plan plan = (Plan) o;
        return Objects.equals(planID, plan.planID) && Objects.equals(purchases, plan.purchases) && Objects.equals(name, plan.name) && Price == plan.Price && duration == plan.duration && Objects.equals(pictureID, plan.pictureID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planID, purchases, name, Price, duration, pictureID);
    }
    

    @Override
    public String toString() {
        return "{" +
            " planID='" + getPlanID() + "'" +
            ", purchases='" + getPurchases() + "'" +
            ", name='" + getName() + "'" +
            ", Price='" + getPrice() + "'" +
            ", duration='" + getDuration() + "'" +
            ", pictureID='" + getPictureID() + "'" +
            "}";
    }
    
}
