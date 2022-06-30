package com.ui.model;

public class Product {

    private final Double price;
    private final String name;

    public Product(Double price, String name) {
        this.name = name;
        this.price = price;
    }

    public double Subtotal (int quantity) {
        return this.price * (double)quantity;
    }
}
