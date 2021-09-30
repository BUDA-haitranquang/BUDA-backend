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

@Entity
@Table(name = "Salary_log", indexes = {
    @Index(columnList = "user_id", name = "salary_log_user_id_index"),
    @Index(columnList = "staff_id", name = "salary_log_staff_id_index")
})
public class SalaryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_log_id")
    private Long salaryLogID;
    @Column(name = "staff_id")
    private Long staffID;
    private double salary;
    private ZonedDateTime creationTime;
    @Column(name = "user_id")
    private Long userID;


    public SalaryLog() {
    }

    public SalaryLog(Long salaryLogID, Long staffID, double salary, ZonedDateTime creationTime, Long userID) {
        this.salaryLogID = salaryLogID;
        this.staffID = staffID;
        this.salary = salary;
        this.creationTime = creationTime;
        this.userID = userID;
    }

    public Long getSalaryLogID() {
        return this.salaryLogID;
    }

    public void setSalaryLogID(Long salaryLogID) {
        this.salaryLogID = salaryLogID;
    }

    public Long getStaffID() {
        return this.staffID;
    }

    public void setStaffID(Long staffID) {
        this.staffID = staffID;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public SalaryLog salaryLogID(Long salaryLogID) {
        setSalaryLogID(salaryLogID);
        return this;
    }

    public SalaryLog staffID(Long staffID) {
        setStaffID(staffID);
        return this;
    }

    public SalaryLog salary(double salary) {
        setSalary(salary);
        return this;
    }

    public SalaryLog creationTime(ZonedDateTime creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    public SalaryLog userID(Long userID) {
        setUserID(userID);
        return this;
    }

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
