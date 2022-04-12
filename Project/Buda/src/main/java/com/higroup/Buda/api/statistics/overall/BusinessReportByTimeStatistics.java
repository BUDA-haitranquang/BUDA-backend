package com.higroup.Buda.api.statistics.overall;

public class BusinessReportByTimeStatistics {
    private String timePeriod;
    private Double revenue;
    private Double expense;
    private Double profit;

    public BusinessReportByTimeStatistics() {
    }

    public BusinessReportByTimeStatistics(String timePeriod, Double revenue, Double expense, Double profit) {
        this.timePeriod = timePeriod;
        this.revenue = revenue;
        this.expense = expense;
        this.profit = profit;
    }

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

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
