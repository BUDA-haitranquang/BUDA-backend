package com.higroup.Buda.customDTO;

public class RevenueByTimePeriodStatistics {

    private String timeFrom;
    private String timeTo;
    private Double revenue;
    public RevenueByTimePeriodStatistics(){}
    public RevenueByTimePeriodStatistics(String timeFrom, String timeTo, Double revenue) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.revenue = revenue;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}
