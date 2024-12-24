package com.dw.jdbcapp.dto;

import org.hibernate.mapping.Value;

public class ProductDTO {
    private int productId;
    private String productName;
    private double unitPrice;
    private int stock;
    private Double StockValue;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, double unitPrice, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getStockValue() {
        return StockValue;
    }

    public void setStockValue(Double stockValue) {
        StockValue = stockValue;
    }
}
