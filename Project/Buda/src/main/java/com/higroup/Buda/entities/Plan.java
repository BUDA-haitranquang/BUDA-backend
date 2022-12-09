package com.higroup.Buda.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.higroup.Buda.entities.enumeration.PlanType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"purchases"})
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    Long planID;
    @OneToMany(mappedBy = "plan",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JsonManagedReference(value = "plan - purchase")
    private Set<Purchase> purchases;
    @Column(length = 50)
    private String name;
    private double price;
    private Integer duration;
    @Column(name = "picture_id")
    private Long pictureID;
    @Column(length = 200)
    private String description;
    @Column(name = "plan_type")
    @Enumerated(EnumType.STRING)
    private PlanType planType;

    @Override
    public int hashCode() {
        return Objects.hash(planID, purchases, name, price, duration, pictureID);
    }

    @Override
    public String toString() {
        return "{" +
            " planID='" + planID + "'" +
            ", purchases='" + purchases + "'" +
            ", name='" + name + "'" +
            ", Price='" + price + "'" +
            ", duration='" + duration + "'" +
            ", pictureID='" + pictureID + "'" +
            "}";
    }
    
}
