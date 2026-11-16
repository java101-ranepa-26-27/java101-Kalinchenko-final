package ru.ranepa.carsalon;

import java.util.ArrayList;

public class Salon {
    private ArrayList<Vehicle> catalog = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        catalog.add(vehicle);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public ArrayList<Vehicle> getCatalog() {
        return catalog;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    // продажа: false, если номера неверные или машина уже продана
    public boolean sellVehicle(int vehicleIndex, int clientIndex) {
        if (vehicleIndex < 0 || vehicleIndex >= catalog.size()) {
            return false;
        }
        if (clientIndex < 0 || clientIndex >= clients.size()) {
            return false;
        }
        return catalog.get(vehicleIndex).sell(clients.get(clientIndex));
    }

    // тест-драйв: false, если номера неверные или машина продана
    public boolean bookTestDrive(int vehicleIndex, int clientIndex) {
        if (vehicleIndex < 0 || vehicleIndex >= catalog.size()) {
            return false;
        }
        if (clientIndex < 0 || clientIndex >= clients.size()) {
            return false;
        }
        return catalog.get(vehicleIndex).bookTestDrive(clients.get(clientIndex));
    }

    // поиск по марке или модели; если ничего нет, список пустой
    public ArrayList<Vehicle> search(String text) {
        ArrayList<Vehicle> result = new ArrayList<>();
        if (text == null || text.trim().isEmpty()) {
            return result;
        }
        String lowerText = text.trim().toLowerCase();
        for (int i = 0; i < catalog.size(); i++) {
            Vehicle vehicle = catalog.get(i);
            String brand = vehicle.getBrand().toLowerCase();
            String model = vehicle.getModel().toLowerCase();
            if (brand.contains(lowerText) || model.contains(lowerText)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    // фильтр: только машины дешевле указанной суммы
    public ArrayList<Vehicle> findCheaperThan(double limit) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getPrice() < limit) {
                result.add(catalog.get(i));
            }
        }
        return result;
    }

    // средняя цена; для пустого каталога 0
    public double getAveragePrice() {
        if (catalog.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < catalog.size(); i++) {
            sum = sum + catalog.get(i).getPrice();
        }
        return sum / catalog.size();
    }

    // самая дорогая машина; для пустого каталога null
    public Vehicle getMostExpensive() {
        if (catalog.isEmpty()) {
            return null;
        }
        Vehicle best = catalog.get(0);
        for (int i = 1; i < catalog.size(); i++) {
            if (catalog.get(i).getPrice() > best.getPrice()) {
                best = catalog.get(i);
            }
        }
        return best;
    }

    public int getSoldCount() {
        int count = 0;
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).isSold()) {
                count++;
            }
        }
        return count;
    }
}