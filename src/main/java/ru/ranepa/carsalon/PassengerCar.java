package ru.ranepa.carsalon;

public class PassengerCar extends Vehicle {
    private static final int DEFAULT_SEATS = 5;

    private int seats;

    public PassengerCar(String brand, String model, int year, double price, int seats) {
        super(brand, model, year, price);
        setSeats(seats);
    }

    public PassengerCar(String brand, String model) {
        super(brand, model);
        this.seats = DEFAULT_SEATS;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        if (seats <= 0) {
            throw new IllegalArgumentException("Число мест должно быть больше нуля.");
        }
        this.seats = seats;
    }

    public String toString() {
        return "Легковой: " + super.toString() + ", мест: " + seats;
    }
}