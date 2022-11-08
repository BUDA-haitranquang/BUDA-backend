package com.higroup.Buda.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Supplier", indexes = {
    @Index(columnList = "user_id", name = "supplier_user_id_index"),
    @Index(columnList = "phoneNumber", name = "supplier_phone_number_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierID;
    @Column(length = 50)
    private String email;
    @Column(name = "picture_id")
    private Long pictureID;
    @Column(length = 50)
    private String name;
    @Column(length = 220)
    private String address;
    @Column(length = 15)
    private String phoneNumber;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Override
    public int hashCode() {
        return Objects.hash(supplierID);
    }

    @Override
    public String toString() {
        return "{" +
            " supplierID='" + getSupplierID() + "'" +
            ", email='" + getEmail() + "'" +
            ", pictureID='" + getPictureID() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }


}
