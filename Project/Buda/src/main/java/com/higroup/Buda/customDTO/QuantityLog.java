package com.higroup.Buda.customDTO;
import javax.validation.constraints.NotNull;
public class QuantityLog {
    @NotNull
    private Integer amountLeftChange;
    private String message;

    public Integer getAmountLeftChange() {
        return amountLeftChange;
    }

    public void setAmountLeftChange(Integer amountLeftChange) {
        this.amountLeftChange = amountLeftChange;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QuantityLog() {
    }

    public QuantityLog(Integer amountLeftChange, String message) {
        this.amountLeftChange = amountLeftChange;
        this.message = message;
    }
}
