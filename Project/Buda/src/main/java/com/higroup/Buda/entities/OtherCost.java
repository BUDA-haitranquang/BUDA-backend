package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.higroup.Buda.entities.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Other_cost", indexes = {
    @Index(columnList = "user_id", name = "other_cost_user_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtherCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "other_cost_id")
    private Long otherCostID;
    @Column(name = "user_id")
    private Long userID;
    @Column(columnDefinition = "Double default 0.0")
    private Double totalCost;
    private ZonedDateTime creationTime;
    @Column(length = 200)
    private String name;
    @Column(length = 2000)
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PREPARING;
    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private Boolean visible = Boolean.TRUE;


    @Override
    public int hashCode() {
        return Objects.hash(otherCostID);
    }

    @Override
    public String toString() {
        return "{" +
            " otherCostID='" + getOtherCostID() + "'" +
            ", userID='" + getUserID() + "'" +
            ", totalCost='" + getTotalCost() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
  
}
