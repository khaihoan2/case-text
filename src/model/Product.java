package com.codegym.model;

public class Product implements Comparable<Product>{
    private String productId;
    private String productName;
    private double price;
    private int amount;
    private String description;

    public Product(String productId, String productName, double price, int amount, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int compareTo(Product product) {
        return Double.compare(this.price, product.getPrice());
    }
}