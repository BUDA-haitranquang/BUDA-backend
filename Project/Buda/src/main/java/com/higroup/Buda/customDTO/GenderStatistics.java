package com.higroup.Buda.customDTO;

import com.higroup.Buda.entities.Gender;

public class GenderStatistics {
    private Gender gender;
    private Double totalSpend;
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Double getTotalSpend() {
        return totalSpend;
    }
    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }
    public GenderStatistics() {
    }
    public GenderStatistics(Gender gender, Double totalSpend) {
        this.gender = gender;
        this.totalSpend = totalSpend;
    }
    
}
