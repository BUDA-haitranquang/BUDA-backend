package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
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
@Table(name = "Salary_log", indexes = {
    @Index(columnList = "user_id", name = "salary_log_user_id_index"),
    @Index(columnList = "staff_id", name = "salary_log_staff_id_index")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_log_id")
    private Long salaryLogID;
    @Column(name = "staff_id")
    private Long staffID;
    private Double salary;
    private ZonedDateTime creationTime;
    @Column(name = "user_id")
    private Long userID;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SalaryLog)) {
            return false;
        }
        SalaryLog salaryLog = (SalaryLog) o;
        return Objects.equals(salaryLogID, salaryLog.salaryLogID) && Objects.equals(staffID, salaryLog.staffID) && salary == salaryLog.salary && Objects.equals(creationTime, salaryLog.creationTime) && Objects.equals(userID, salaryLog.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryLogID, staffID, salary, creationTime, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " salaryLogID='" + getSalaryLogID() + "'" +
            ", staffID='" + getStaffID() + "'" +
            ", salary='" + getSalary() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }
    
}
