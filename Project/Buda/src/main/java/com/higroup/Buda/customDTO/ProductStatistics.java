package com.higroup.Buda.customDTO;

public class ProductStatistics {
    private Long productID;
    private String name;
    private Double totalRevenue;
    public Long getProductID() {
        return productID;
    }
    public void setProductID(Long productID) {
        this.productID = productID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getTotalRevenue() {
        return totalRevenue;
    }
    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    public ProductStatistics(Long productID, String name, Double totalRevenue) {
        this.productID = productID;
        this.name = name;
        this.totalRevenue = totalRevenue;
    }
    public ProductStatistics() {
    }
    
    
}
