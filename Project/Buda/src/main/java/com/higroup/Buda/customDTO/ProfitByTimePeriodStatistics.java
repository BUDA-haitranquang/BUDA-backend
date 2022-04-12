package com.higroup.Buda.customDTO;

public class ProfitByTimePeriodStatistics {
    private String timeFrom;
    private String timeTo;
    private Double revenue;
    private Double expense;
    private Double profit;

    public ProfitByTimePeriodStatistics(String timeFrom, String timeTo, Double revenue, Double expense, Double profit) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.revenue = revenue;
        this.expense = expense;
        this.profit = profit;
    }

    public ProfitByTimePeriodStatistics() {
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

    public Double getExpense() {
        return expense;
    }

    public Double getProfit() {
        return profit;
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

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
