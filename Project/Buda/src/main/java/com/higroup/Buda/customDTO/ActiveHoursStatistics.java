package com.higroup.Buda.customDTO;

public class ActiveHoursStatistics {
    private String hour;
    private Long totalCount;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public ActiveHoursStatistics() {
    }

    public ActiveHoursStatistics(String hour, Long totalCount) {
        this.hour = hour;
        this.totalCount = totalCount;
    }
}
