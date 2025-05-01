package com.example.coffeeorder;

public class Coffee {
    private String name;
    private int imageId;
    private int pointPerCup;
    private double price;
    private int pointToRedeem;

    public int getPointToRedeem() {
        return pointToRedeem;
    }

    public void setPointToRedeem(int pointToRedeem) {
        this.pointToRedeem = pointToRedeem;
    }

    public int getPointPerCup() {
        return pointPerCup;
    }

    public void setPointPerCup(int pointPerCup) {
        this.pointPerCup = pointPerCup;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Coffee(String name, int imageId, int pointPerCup, double price, int pointToRedeem) {
        this.name = name;
        this.imageId = imageId;
        this.pointPerCup = pointPerCup;
        this.price = price;
        this.pointToRedeem = pointToRedeem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

