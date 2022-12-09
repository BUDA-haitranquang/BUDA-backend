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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Fixed_cost", indexes = {
    @Index(columnList = "user_id", name = "fixed_cost_user_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private Boolean visible = Boolean.TRUE;
    @OneToMany(mappedBy = "fixedCost", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "fixed_cost - fixed_cost_bill")
    @Fetch(FetchMode.SUBSELECT)
    private Set<FixedCostBill> fixedCostBills;

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
