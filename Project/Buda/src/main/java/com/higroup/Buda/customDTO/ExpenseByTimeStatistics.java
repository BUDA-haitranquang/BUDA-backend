package com.higroup.Buda.customDTO;

public class ExpenseByTimeStatistics {
    private String timePeriod;
    private Double expense;
    public String getTimePeriod() {
        return timePeriod;
    }
    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }
    public Double getExpense() {
        return expense;
    }
    public void setExpense(Double expense) {
        this.expense = expense;
    }
    public ExpenseByTimeStatistics() {
    }
    public ExpenseByTimeStatistics(String timePeriod, Double expense) {
        this.timePeriod = timePeriod;
        this.expense = expense;
    }
}
