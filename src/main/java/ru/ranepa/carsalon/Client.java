package ru.ranepa.carsalon;

public class Client {
    private String name;
    private String phone;

    public Client(String name, String phone) {
        setName(name);
        setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Телефон не может быть пустым.");
        }
        this.phone = phone;
    }

    public String toString() {
        return name + ", телефон: " + phone;
    }
}