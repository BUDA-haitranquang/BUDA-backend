package com.higroup.Buda.entities;

import java.util.Objects;
import java.util.Set;

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
@Table(name = "Fixed_cost")
public class FixedCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fixedCostID;
    private String name;
    @Column(length = 1000)
    private String description;
    private double moneyAmount;
    private int period;
    private Long userID;
    @OneToMany(mappedBy = "fixedCost", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<FixedCostBill> fixedCostBills;

    public FixedCost(Long fixedCostID, String name, String description, double moneyAmount, int period, Long userID, Set<FixedCostBill> fixedCostBills) {
        this.fixedCostID = fixedCostID;
        this.name = name;
        this.description = description;
        this.moneyAmount = moneyAmount;
        this.period = period;
        this.userID = userID;
        this.fixedCostBills = fixedCostBills;
    }

    public Set<FixedCostBill> getFixedCostBills() {
        return this.fixedCostBills;
    }

    public void setFixedCostBills(Set<FixedCostBill> fixedCostBills) {
        this.fixedCostBills = fixedCostBills;
    }

    public FixedCost fixedCostBills(Set<FixedCostBill> fixedCostBills) {
        setFixedCostBills(fixedCostBills);
        return this;
    }

    public FixedCost() {
    }

    public FixedCost(Long fixedCostID, String name, String description, double moneyAmount, int period, Long userID) {
        this.fixedCostID = fixedCostID;
        this.name = name;
        this.description = description;
        this.moneyAmount = moneyAmount;
        this.period = period;
        this.userID = userID;
    }

    public Long getFixedCostID() {
        return this.fixedCostID;
    }

    public void setFixedCostID(Long fixedCostID) {
        this.fixedCostID = fixedCostID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMoneyAmount() {
        return this.moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public FixedCost fixedCostID(Long fixedCostID) {
        setFixedCostID(fixedCostID);
        return this;
    }

    public FixedCost name(String name) {
        setName(name);
        return this;
    }

    public FixedCost description(String description) {
        setDescription(description);
        return this;
    }

    public FixedCost moneyAmount(double moneyAmount) {
        setMoneyAmount(moneyAmount);
        return this;
    }

    public FixedCost period(int period) {
        setPeriod(period);
        return this;
    }

    public FixedCost userID(Long userID) {
        setUserID(userID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FixedCost)) {
            return false;
        }
        FixedCost fixedCost = (FixedCost) o;
        return Objects.equals(fixedCostID, fixedCost.fixedCostID) && Objects.equals(name, fixedCost.name) && Objects.equals(description, fixedCost.description) && moneyAmount == fixedCost.moneyAmount && period == fixedCost.period && Objects.equals(userID, fixedCost.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixedCostID, name, description, moneyAmount, period, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " fixedCostID='" + getFixedCostID() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", moneyAmount='" + getMoneyAmount() + "'" +
            ", period='" + getPeriod() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }

}
