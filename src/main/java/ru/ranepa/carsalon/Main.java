package ru.ranepa.carsalon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в автосалон!");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Vehicle> catalog = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();

        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine();

            int command;
            try {
                command = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести число от 0 до 8.");
                continue;
            }

            if (command == 1) {
                addVehicle(scanner, catalog);
            } else if (command == 2) {
                showCatalog(catalog);
            } else if (command == 3) {
                addClient(scanner, clients);
            } else if (command == 4) {
                showClients(clients);
            } else if (command == 5) {
                addCarQuick(scanner, catalog);
            } else if (command == 6) {
                System.out.println("Всего автомобилей заведено: " + Vehicle.getTotalVehicles());
            } else if (command == 7) {
                sellVehicle(scanner, catalog, clients);
            } else if (command == 8) {
                bookTestDrive(scanner, catalog, clients);
            } else if (command == 0) {
                System.out.println("До свидания!");
                running = false;
            } else {
                System.out.println("Такого пункта нет в меню.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Меню ===");
        System.out.println("1 - Добавить автомобиль");
        System.out.println("2 - Показать каталог");
        System.out.println("3 - Добавить клиента");
        System.out.println("4 - Показать клиентов");
        System.out.println("5 - Быстро добавить легковой (марка и модель)");
        System.out.println("6 - Сколько всего автомобилей заведено");
        System.out.println("7 - Продать автомобиль");
        System.out.println("8 - Записать клиента на тест-драйв");
        System.out.println("0 - Выход");
        System.out.print("Выберите пункт: ");
    }

    private static void addVehicle(Scanner scanner, ArrayList<Vehicle> catalog) {
        System.out.print("Тип (1 - легковой, 2 - грузовой): ");
        String type = scanner.nextLine();
        if (!type.equals("1") && !type.equals("2")) {
            System.out.println("Ошибка: тип должен быть 1 или 2.");
            return;
        }

        System.out.print("Марка: ");
        String brand = scanner.nextLine();
        System.out.print("Модель: ");
        String model = scanner.nextLine();

        try {
            System.out.print("Год выпуска: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Цена: ");
            double price = Double.parseDouble(scanner.nextLine());

            Vehicle vehicle;
            if (type.equals("1")) {
                System.out.print("Число мест: ");
                int seats = Integer.parseInt(scanner.nextLine());
                vehicle = new PassengerCar(brand, model, year, price, seats);
            } else {
                System.out.print("Грузоподъёмность (т): ");
                double capacity = Double.parseDouble(scanner.nextLine());
                vehicle = new Truck(brand, model, year, price, capacity);
            }
            catalog.add(vehicle);
            System.out.println("Автомобиль добавлен.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: нужно ввести число.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void addCarQuick(Scanner scanner, ArrayList<Vehicle> catalog) {
        System.out.print("Марка: ");
        String brand = scanner.nextLine();
        System.out.print("Модель: ");
        String model = scanner.nextLine();

        try {
            PassengerCar car = new PassengerCar(brand, model);
            catalog.add(car);
            System.out.println("Автомобиль добавлен (год, цена и число мест по умолчанию).");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showCatalog(ArrayList<Vehicle> catalog) {
        if (catalog.isEmpty()) {
            System.out.println("Каталог пока пуст.");
            return;
        }
        System.out.println("Каталог:");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println((i + 1) + ". " + catalog.get(i));
        }
    }

    private static void addClient(Scanner scanner, ArrayList<Client> clients) {
        System.out.print("Имя клиента: ");
        String name = scanner.nextLine();
        System.out.print("Телефон: ");
        String phone = scanner.nextLine();

        try {
            Client client = new Client(name, phone);
            clients.add(client);
            System.out.println("Клиент добавлен.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showClients(ArrayList<Client> clients) {
        if (clients.isEmpty()) {
            System.out.println("Список клиентов пуст.");
            return;
        }
        System.out.println("Клиенты:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i));
        }
    }

    // возвращает номер в списке (с нуля) или -1, если ввод неверный
    private static int readIndex(Scanner scanner, String text, int size) {
        System.out.print(text);
        try {
            int number = Integer.parseInt(scanner.nextLine());
            if (number < 1 || number > size) {
                System.out.println("Ошибка: такого номера нет в списке.");
                return -1;
            }
            return number - 1;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: нужно ввести число.");
            return -1;
        }
    }

    private static void sellVehicle(Scanner scanner, ArrayList<Vehicle> catalog, ArrayList<Client> clients) {
        if (catalog.isEmpty() || clients.isEmpty()) {
            System.out.println("Нужен хотя бы один автомобиль и один клиент.");
            return;
        }
        showCatalog(catalog);
        int carIndex = readIndex(scanner, "Номер автомобиля: ", catalog.size());
        if (carIndex == -1) {
            return;
        }
        showClients(clients);
        int clientIndex = readIndex(scanner, "Номер клиента: ", clients.size());
        if (clientIndex == -1) {
            return;
        }

        boolean success = catalog.get(carIndex).sell(clients.get(clientIndex));
        if (success) {
            System.out.println("Автомобиль продан.");
        } else {
            System.out.println("Ошибка: этот автомобиль уже продан.");
        }
    }

    private static void bookTestDrive(Scanner scanner, ArrayList<Vehicle> catalog, ArrayList<Client> clients) {
        if (catalog.isEmpty() || clients.isEmpty()) {
            System.out.println("Нужен хотя бы один автомобиль и один клиент.");
            return;
        }
        showCatalog(catalog);
        int carIndex = readIndex(scanner, "Номер автомобиля: ", catalog.size());
        if (carIndex == -1) {
            return;
        }
        showClients(clients);
        int clientIndex = readIndex(scanner, "Номер клиента: ", clients.size());
        if (clientIndex == -1) {
            return;
        }

        boolean success = catalog.get(carIndex).bookTestDrive(clients.get(clientIndex));
        if (success) {
            System.out.println("Клиент записан на тест-драйв.");
        } else {
            System.out.println("Ошибка: автомобиль уже продан, тест-драйв невозможен.");
        }
    }
}