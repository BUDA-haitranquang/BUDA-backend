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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Membership_type", indexes = {
    @Index(columnList = "user_id", name = "membership_type_user_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Double minimumSpend;

    @Override
    public int hashCode() {
        return Objects.hash(membershipTypeID, membershipName, description, userID, discount, minimumSpend);
    }

    @Override
    public String toString() {
        return "{" +
//            " membershipTypeID='" + getMembershipTypeID() + "'" +
            ", membershipName='" + getMembershipName() + "'" +
            ", description='" + getDescription() + "'" +
//            ", userID='" + getUserID() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", minimumSpend='" + getMinimumSpend() + "'" +
            "}";
    }
    
}
