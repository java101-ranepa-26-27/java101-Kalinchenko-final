package ru.ranepa.carsalon;

public class Car {
    private String brand;
    private String model;
    private int year;
    private double price;
    private boolean sold;

    public Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.sold = false;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String toString() {
        String status;
        if (sold) {
            status = "продана";
        } else {
            status = "в наличии";
        }
        return brand + " " + model + ", " + year + " г., " + price + " руб. (" + status + ")";
    }
}