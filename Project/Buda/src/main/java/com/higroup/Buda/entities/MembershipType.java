package com.higroup.Buda.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Membership_type")
public class MembershipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipTypeID;
    private String name;
    private String description;
    private Long userID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "discountID", nullable = true)
    @JsonBackReference
    private double minimumSpend;
    public MembershipType() {
    }

    public MembershipType(Long membershipTypeID, String name, String description, Long userID, double minimumSpend) {
        this.membershipTypeID = membershipTypeID;
        this.name = name;
        this.description = description;
        this.userID = userID;
        this.minimumSpend = minimumSpend;
    }

    public Long getMembershipTypeID() {
        return this.membershipTypeID;
    }

    public void setMembershipTypeID(Long membershipTypeID) {
        this.membershipTypeID = membershipTypeID;
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

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public double getMinimumSpend() {
        return this.minimumSpend;
    }

    public void setMinimumSpend(double minimumSpend) {
        this.minimumSpend = minimumSpend;
    }

    public MembershipType membershipTypeID(Long membershipTypeID) {
        setMembershipTypeID(membershipTypeID);
        return this;
    }

    public MembershipType name(String name) {
        setName(name);
        return this;
    }

    public MembershipType description(String description) {
        setDescription(description);
        return this;
    }

    public MembershipType userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public MembershipType minimumSpend(double minimumSpend) {
        setMinimumSpend(minimumSpend);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MembershipType)) {
            return false;
        }
        MembershipType membershipType = (MembershipType) o;
        return Objects.equals(membershipTypeID, membershipType.membershipTypeID) && Objects.equals(name, membershipType.name) && Objects.equals(description, membershipType.description) && Objects.equals(userID, membershipType.userID) && minimumSpend == membershipType.minimumSpend;
    }

    @Override
    public int hashCode() {
        return Objects.hash(membershipTypeID, name, description, userID, minimumSpend);
    }

    @Override
    public String toString() {
        return "{" +
            " membershipTypeID='" + getMembershipTypeID() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", userID='" + getUserID() + "'" +
            ", minimumSpend='" + getMinimumSpend() + "'" +
            "}";
    }

}
