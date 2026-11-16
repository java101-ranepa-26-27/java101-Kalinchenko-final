package ru.ranepa.carsalon;

public class Car {
    private static final int CURRENT_YEAR = 2026;
    private static final int MAX_CAR_AGE = 40;
    private static final int DEFAULT_YEAR = 2026;
    private static final double DEFAULT_PRICE = 1000000;

    private static int totalCars = 0;

    private String brand;
    private String model;
    private int year;
    private double price;
    private boolean sold;

    // полный конструктор
    public Car(String brand, String model, int year, double price) {
        setBrand(brand);
        setModel(model);
        setYear(year);
        setPrice(price);
        this.sold = false;
        totalCars++;
    }

    // сокращённый конструктор: только марка и модель
    public Car(String brand, String model) {
        this(brand, model, DEFAULT_YEAR, DEFAULT_PRICE);
    }

    public static int getTotalCars() {
        return totalCars;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка не может быть пустой.");
        }
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой.");
        }
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year > CURRENT_YEAR) {
            throw new IllegalArgumentException("Год выпуска не может быть в будущем.");
        }
        if (year < CURRENT_YEAR - MAX_CAR_AGE) {
            throw new IllegalArgumentException("Машины старше " + MAX_CAR_AGE + " лет не принимаются.");
        }
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля.");
        }
        this.price = price;
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