package com.higroup.Buda.customDTO;

public class RevenueByTimeStatistics {
    private String timePeriod;
    private Double revenue;
    public String getTimePeriod() {
        return timePeriod;
    }
    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }
    public Double getRevenue() {
        return revenue;
    }
    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
    public RevenueByTimeStatistics(String timePeriod, Double revenue) {
        this.timePeriod = timePeriod;
        this.revenue = revenue;
    }
    public RevenueByTimeStatistics() {
    }
    
    
}
