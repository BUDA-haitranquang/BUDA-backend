package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.higroup.Buda.entities.enumeration.LeftLogType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Product_left_log", indexes = {
    @Index(columnList = "user_id", name = "product_left_log_user_id_index"),
    @Index(columnList = "product_id", name = "product_left_log_product_id_index"),
    @Index(columnList = "staff_id", name = "product_left_log_staff_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductLeftLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_left_log_id")
    private Long productLeftLogID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "product_ID", nullable = true)
    private Product product;
    private Integer amountLeftChange;
    private ZonedDateTime creationTime;
    @Column(name = "staff_id")
    private Long staffID;
    @Column(length = 1000)
    private String message;
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "left_log_type")
    @Enumerated(EnumType.STRING)
    private LeftLogType leftLogType;

    @Override
    public int hashCode() {
        return Objects.hash(productLeftLogID, product, amountLeftChange, creationTime, staffID, message, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " productLeftLogID='" + getProductLeftLogID() + "'" +
            ", product='" + getProduct() + "'" +
            ", amountLeftChange='" + getAmountLeftChange() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", staffID='" + getStaffID() + "'" +
            ", message='" + getMessage() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }

}
