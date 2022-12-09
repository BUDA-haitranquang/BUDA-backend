package com.higroup.Buda.customDTO;

import com.higroup.Buda.entities.enumeration.AgeGroup;

public class AgeGroupStatistics {
    private AgeGroup ageGroup;
    private Double totalSpend;
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }
    public Double getTotalSpend() {
        return totalSpend;
    }
    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }
    public AgeGroupStatistics() {
    }
    public AgeGroupStatistics(AgeGroup ageGroup, Double totalSpend) {
        this.ageGroup = ageGroup;
        this.totalSpend = totalSpend;
    }
    
}
