package com.higroup.Buda.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Membership_type", indexes = {
    @Index(columnList = "user_id", name = "membership_type_user_id_index")
})
public class MembershipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_type_id")
    private Long membershipTypeID;
    private String membershipName;
    @Column(length = 1000)
    private String description;
    @Column(name = "user_id")
    private Long userID;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "discount_ID", nullable = true)
    @JsonBackReference
    private Discount discount;
    private double minimumSpend;

    public MembershipType() {
    }

    public MembershipType(Long membershipTypeID, String membershipName, String description, Long userID, Discount discount, double minimumSpend) {
        this.membershipTypeID = membershipTypeID;
        this.membershipName = membershipName;
        this.description = description;
        this.userID = userID;
        this.discount = discount;
        this.minimumSpend = minimumSpend;
    }

    public Long getMembershipTypeID() {
        return this.membershipTypeID;
    }

    public void setMembershipTypeID(Long membershipTypeID) {
        this.membershipTypeID = membershipTypeID;
    }

    public String getMembershipName() {
        return this.membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
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

    public Discount getDiscount() {
        return this.discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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

    public MembershipType membershipName(String membershipName) {
        setMembershipName(membershipName);
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

    public MembershipType discount(Discount discount) {
        setDiscount(discount);
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
        return Objects.equals(membershipTypeID, membershipType.membershipTypeID) && Objects.equals(membershipName, membershipType.membershipName) && Objects.equals(description, membershipType.description) && Objects.equals(userID, membershipType.userID) && Objects.equals(discount, membershipType.discount) && minimumSpend == membershipType.minimumSpend;
    }

    @Override
    public int hashCode() {
        return Objects.hash(membershipTypeID, membershipName, description, userID, discount, minimumSpend);
    }

    @Override
    public String toString() {
        return "{" +
            " membershipTypeID='" + getMembershipTypeID() + "'" +
            ", membershipName='" + getMembershipName() + "'" +
            ", description='" + getDescription() + "'" +
            ", userID='" + getUserID() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", minimumSpend='" + getMinimumSpend() + "'" +
            "}";
    }
    
}
