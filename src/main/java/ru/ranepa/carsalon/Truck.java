package ru.ranepa.carsalon;

public class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String brand, String model, int year, double price, double loadCapacity) {
        super(brand, model, year, price);
        setLoadCapacity(loadCapacity);
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        if (loadCapacity <= 0) {
            throw new IllegalArgumentException("Грузоподъёмность должна быть больше нуля.");
        }
        this.loadCapacity = loadCapacity;
    }

    public String toString() {
        return "Грузовой: " + super.toString() + ", грузоподъёмность: " + loadCapacity + " т";
    }
}