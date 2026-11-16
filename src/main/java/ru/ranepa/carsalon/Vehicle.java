package ru.ranepa.carsalon;

public class Vehicle {
    private static final int CURRENT_YEAR = 2026;
    private static final int MAX_CAR_AGE = 40;
    private static final int DEFAULT_YEAR = 2026;
    private static final double DEFAULT_PRICE = 1000000;

    private static int totalVehicles = 0;

    private String brand;
    private String model;
    private int year;
    private double price;
    private boolean sold;
    private Client buyer;
    private Client testDriveClient;

    // полный конструктор
    public Vehicle(String brand, String model, int year, double price) {
        setBrand(brand);
        setModel(model);
        setYear(year);
        setPrice(price);
        this.sold = false;
        totalVehicles++;
    }

    // сокращённый конструктор
    public Vehicle(String brand, String model) {
        this(brand, model, DEFAULT_YEAR, DEFAULT_PRICE);
    }

    public static int getTotalVehicles() {
        return totalVehicles;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка не может быть пустой.");
        }
        this.brand = brand.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой.");
        }
        this.model = model.trim();
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

    // продажа: false, если машина уже продана
    public boolean sell(Client buyer) {
        if (sold) {
            return false;
        }
        this.sold = true;
        this.buyer = buyer;
        this.testDriveClient = null;
        return true;
    }

    // запись на тест-драйв: false, если машина продана
    public boolean bookTestDrive(Client client) {
        if (sold) {
            return false;
        }
        this.testDriveClient = client;
        return true;
    }

    public String toString() {
        String status;
        if (sold) {
            status = "продана клиенту " + buyer.getName();
        } else if (testDriveClient != null) {
            status = "в наличии, тест-драйв: " + testDriveClient.getName();
        } else {
            status = "в наличии";
        }
        return brand + " " + model + ", " + year + " г., " + price + " руб. (" + status + ")";
    }
}