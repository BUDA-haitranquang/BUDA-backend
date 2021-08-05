package com.higroup.Buda.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Salary_log")
public class SalaryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryLogID;
    private Long staffID;
    private double salary;
    private ZonedDateTime creationDate;
    private Long userID;

    public SalaryLog() {
    }

    public SalaryLog(Long salaryLogID, Long staffID, double salary, ZonedDateTime creationDate, Long userID) {
        this.salaryLogID = salaryLogID;
        this.staffID = staffID;
        this.salary = salary;
        this.creationDate = creationDate;
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

    public ZonedDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
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

    public SalaryLog creationDate(ZonedDateTime creationDate) {
        setCreationDate(creationDate);
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
        return Objects.equals(salaryLogID, salaryLog.salaryLogID) && Objects.equals(staffID, salaryLog.staffID) && salary == salaryLog.salary && Objects.equals(creationDate, salaryLog.creationDate) && Objects.equals(userID, salaryLog.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryLogID, staffID, salary, creationDate, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " salaryLogID='" + getSalaryLogID() + "'" +
            ", staffID='" + getStaffID() + "'" +
            ", salary='" + getSalary() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }

}
