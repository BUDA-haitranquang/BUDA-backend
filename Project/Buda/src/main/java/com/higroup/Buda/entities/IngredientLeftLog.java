package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
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

@Entity
@Table(name = "ingredient_left_log", indexes = {
    @Index(columnList = "user_id", name = "ingredient_left_log_user_id_index"),
    @Index(columnList = "ingredient_id", name = "ingredient_left_log_ingredient_id_index"),
    @Index(columnList = "staff_id", name = "ingredient_left_log_staff_id_index")
})
public class IngredientLeftLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_left_log_id")
    private Long ingredientLeftLogID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "ingredient_ID", nullable = true)
    private Ingredient ingredient;
    private Integer amountLeftChange;
    private ZonedDateTime creationTime;
    @Column(name = "staff_id")
    private Long staffID;
    @Column(length = 1000)
    private String message;
    @Column(name = "user_id")
    private Long userID;


    public IngredientLeftLog() {
    }

    public IngredientLeftLog(Long ingredientLeftLogID, Ingredient ingredient, Integer amountLeftChange, ZonedDateTime creationTime, Long staffID, String message, Long userID) {
        this.ingredientLeftLogID = ingredientLeftLogID;
        this.ingredient = ingredient;
        this.amountLeftChange = amountLeftChange;
        this.creationTime = creationTime;
        this.staffID = staffID;
        this.message = message;
        this.userID = userID;
    }

    public Long getIngredientLeftLogID() {
        return this.ingredientLeftLogID;
    }

    public void setIngredientLeftLogID(Long ingredientLeftLogID) {
        this.ingredientLeftLogID = ingredientLeftLogID;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getAmountLeftChange() {
        return this.amountLeftChange;
    }

    public void setAmountLeftChange(Integer amountLeftChange) {
        this.amountLeftChange = amountLeftChange;
    }

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getStaffID() {
        return this.staffID;
    }

    public void setStaffID(Long staffID) {
        this.staffID = staffID;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public IngredientLeftLog ingredientLeftLogID(Long ingredientLeftLogID) {
        setIngredientLeftLogID(ingredientLeftLogID);
        return this;
    }

    public IngredientLeftLog ingredient(Ingredient ingredient) {
        setIngredient(ingredient);
        return this;
    }

    public IngredientLeftLog amountLeftChange(Integer amountLeftChange) {
        setAmountLeftChange(amountLeftChange);
        return this;
    }

    public IngredientLeftLog creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    public IngredientLeftLog staffID(Long staffID) {
        setStaffID(staffID);
        return this;
    }

    public IngredientLeftLog message(String message) {
        setMessage(message);
        return this;
    }

    public IngredientLeftLog userID(Long userID) {
        setUserID(userID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof IngredientLeftLog)) {
            return false;
        }
        IngredientLeftLog ingredientLeftLog = (IngredientLeftLog) o;
        return Objects.equals(ingredientLeftLogID, ingredientLeftLog.ingredientLeftLogID)
                && Objects.equals(ingredient, ingredientLeftLog.ingredient)
                && amountLeftChange == ingredientLeftLog.amountLeftChange
                && Objects.equals(creationTime, ingredientLeftLog.creationTime)
                && Objects.equals(staffID, ingredientLeftLog.staffID)
                && Objects.equals(message, ingredientLeftLog.message)
                && Objects.equals(userID, ingredientLeftLog.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientLeftLogID, ingredient, amountLeftChange, creationTime, staffID, message, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " ingredientLeftLogID='" + getIngredientLeftLogID() + "'" +
            ", ingredient='" + getIngredient() + "'" +
            ", amountLeftChange='" + getAmountLeftChange() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", staffID='" + getStaffID() + "'" +
            ", message='" + getMessage() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }
    

}
