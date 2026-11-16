package ru.ranepa.carsalon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в автосалон!");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Car> catalog = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();

        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine();

            int command;
            try {
                command = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести число от 0 до 4.");
                continue;
            }

            if (command == 1) {
                addCar(scanner, catalog);
            } else if (command == 2) {
                showCatalog(catalog);
            } else if (command == 3) {
                addClient(scanner, clients);
            } else if (command == 4) {
                showClients(clients);
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
        System.out.println("0 - Выход");
        System.out.print("Выберите пункт: ");
    }

    private static void addCar(Scanner scanner, ArrayList<Car> catalog) {
        System.out.print("Марка: ");
        String brand = scanner.nextLine();
        System.out.print("Модель: ");
        String model = scanner.nextLine();

        int year;
        try {
            System.out.print("Год выпуска: ");
            year = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: год должен быть числом.");
            return;
        }

        double price;
        try {
            System.out.print("Цена: ");
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: цена должна быть числом.");
            return;
        }

        Car car = new Car(brand, model, year, price);
        catalog.add(car);
        System.out.println("Автомобиль добавлен.");
    }

    private static void showCatalog(ArrayList<Car> catalog) {
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

        Client client = new Client(name, phone);
        clients.add(client);
        System.out.println("Клиент добавлен.");
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
}