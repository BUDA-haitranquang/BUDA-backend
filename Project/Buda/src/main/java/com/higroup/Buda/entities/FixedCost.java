package com.higroup.Buda.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Fixed_cost", indexes = {
    @Index(columnList = "user_id", name = "fixed_cost_user_id_index")
})
public class FixedCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fixed_cost_id")
    private Long fixedCostID;
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(columnDefinition = "double default 0.0")
    private Double moneyAmount;
    @Column(columnDefinition = "int default 0")
    private Integer period;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "visible", columnDefinition = "boolean default true")
    private Boolean visible;
    @OneToMany(mappedBy = "fixedCost", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "fixed_cost - fixed_cost_bill")
    private Set<FixedCostBill> fixedCostBills;

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public FixedCost(Long fixedCostID, String name, String description, Double moneyAmount, Integer period, Long userID,
            Boolean visible, Set<FixedCostBill> fixedCostBills) {
        this.fixedCostID = fixedCostID;
        this.name = name;
        this.description = description;
        this.moneyAmount = moneyAmount;
        this.period = period;
        this.userID = userID;
        this.visible = visible;
        this.fixedCostBills = fixedCostBills;
    }

    public FixedCost(Long fixedCostID, String name, String description, Double moneyAmount, Integer period, Long userID, Set<FixedCostBill> fixedCostBills) {
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

    public FixedCost(Long fixedCostID, String name, String description, Double moneyAmount, Integer period, Long userID) {
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

    public Double getMoneyAmount() {
        return this.moneyAmount;
    }

    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer period) {
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

    public FixedCost moneyAmount(Double moneyAmount) {
        setMoneyAmount(moneyAmount);
        return this;
    }

    public FixedCost period(Integer period) {
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
        return Objects.equals(fixedCostID, fixedCost.fixedCostID)
                && Objects.equals(name, fixedCost.name)
                && Objects.equals(description, fixedCost.description)
                && moneyAmount == fixedCost.moneyAmount
                && period == fixedCost.period
                && Objects.equals(userID, fixedCost.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixedCostID);
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
