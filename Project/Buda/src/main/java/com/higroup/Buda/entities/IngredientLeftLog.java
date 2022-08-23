package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.higroup.Buda.entities.enumeration.LeftLogType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredient_left_log", indexes = {
    @Index(columnList = "user_id", name = "ingredient_left_log_user_id_index"),
    @Index(columnList = "ingredient_id", name = "ingredient_left_log_ingredient_id_index"),
    @Index(columnList = "staff_id", name = "ingredient_left_log_staff_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientLeftLog {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "ingredient_left_log_id")
    private Long ingredientLeftLogID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = true, referencedColumnName = "ingredient_id")
    private Ingredient ingredient;
    private Integer amountLeftChange;
    private ZonedDateTime creationTime;
    @Column(name = "staff_id")
    private Long staffID;
    @Column(length = 1000)
    private String message;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "leftlog_type")
    @Enumerated(EnumType.STRING)
    private LeftLogType leftLogType;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof IngredientLeftLog)) {
            return false;
        }
        IngredientLeftLog ingredientLeftLog = (IngredientLeftLog) o;
        return Objects.equals(ingredientLeftLogID, ingredientLeftLog.ingredientLeftLogID)
                // && Objects.equals(ingredient, ingredientLeftLog.ingredient)
                && amountLeftChange == ingredientLeftLog.amountLeftChange
                && Objects.equals(creationTime, ingredientLeftLog.creationTime)
                && Objects.equals(staffID, ingredientLeftLog.staffID)
                && Objects.equals(message, ingredientLeftLog.message)
                && Objects.equals(userID, ingredientLeftLog.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientLeftLogID, amountLeftChange, creationTime, staffID, message, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " ingredientLeftLogID='" + getIngredientLeftLogID() + "'" +
            // ", ingredient='" + getIngredient() + "'" +
            ", amountLeftChange='" + getAmountLeftChange() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", staffID='" + getStaffID() + "'" +
            ", message='" + getMessage() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }
    

}
