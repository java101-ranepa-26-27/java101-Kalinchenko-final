package ru.ranepa.carsalon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в автосалон!");

        Scanner scanner = new Scanner(System.in);
        Salon salon = new Salon();

        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine();

            int command;
            try {
                command = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести число от 0 до 11.");
                continue;
            }

            if (command == 1) {
                addVehicle(scanner, salon);
            } else if (command == 2) {
                printList("Каталог", salon.getCatalog());
            } else if (command == 3) {
                addClient(scanner, salon);
            } else if (command == 4) {
                printClients(salon);
            } else if (command == 5) {
                addCarQuick(scanner, salon);
            } else if (command == 6) {
                System.out.println("Всего автомобилей заведено: " + Vehicle.getTotalVehicles());
            } else if (command == 7) {
                sellVehicle(scanner, salon);
            } else if (command == 8) {
                bookTestDrive(scanner, salon);
            } else if (command == 9) {
                searchVehicles(scanner, salon);
            } else if (command == 10) {
                filterVehicles(scanner, salon);
            } else if (command == 11) {
                showStatistics(salon);
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
        System.out.println("9 - Поиск по марке или модели");
        System.out.println("10 - Показать машины дешевле суммы");
        System.out.println("11 - Статистика");
        System.out.println("0 - Выход");
        System.out.print("Выберите пункт: ");
    }

    private static void addVehicle(Scanner scanner, Salon salon) {
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
            salon.addVehicle(vehicle);
            System.out.println("Автомобиль добавлен.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: нужно ввести число.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void addCarQuick(Scanner scanner, Salon salon) {
        System.out.print("Марка: ");
        String brand = scanner.nextLine();
        System.out.print("Модель: ");
        String model = scanner.nextLine();

        try {
            PassengerCar car = new PassengerCar(brand, model);
            salon.addVehicle(car);
            System.out.println("Автомобиль добавлен (год, цена и число мест по умолчанию).");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void addClient(Scanner scanner, Salon salon) {
        System.out.print("Имя клиента: ");
        String name = scanner.nextLine();
        System.out.print("Телефон: ");
        String phone = scanner.nextLine();

        try {
            Client client = new Client(name, phone);
            salon.addClient(client);
            System.out.println("Клиент добавлен.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void printList(String title, ArrayList<Vehicle> list) {
        if (list.isEmpty()) {
            System.out.println("Список пуст: " + title + ".");
            return;
        }
        System.out.println(title + ":");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private static void printClients(Salon salon) {
        ArrayList<Client> clients = salon.getClients();
        if (clients.isEmpty()) {
            System.out.println("Список клиентов пуст.");
            return;
        }
        System.out.println("Клиенты:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i));
        }
    }

    // возвращает номер с нуля или -1, если ввод неверный
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

    private static void sellVehicle(Scanner scanner, Salon salon) {
        if (salon.getCatalog().isEmpty() || salon.getClients().isEmpty()) {
            System.out.println("Нужен хотя бы один автомобиль и один клиент.");
            return;
        }
        printList("Каталог", salon.getCatalog());
        int vehicleIndex = readIndex(scanner, "Номер автомобиля: ", salon.getCatalog().size());
        if (vehicleIndex == -1) {
            return;
        }
        printClients(salon);
        int clientIndex = readIndex(scanner, "Номер клиента: ", salon.getClients().size());
        if (clientIndex == -1) {
            return;
        }

        if (salon.sellVehicle(vehicleIndex, clientIndex)) {
            System.out.println("Автомобиль продан.");
        } else {
            System.out.println("Ошибка: этот автомобиль уже продан.");
        }
    }

    private static void bookTestDrive(Scanner scanner, Salon salon) {
        if (salon.getCatalog().isEmpty() || salon.getClients().isEmpty()) {
            System.out.println("Нужен хотя бы один автомобиль и один клиент.");
            return;
        }
        printList("Каталог", salon.getCatalog());
        int vehicleIndex = readIndex(scanner, "Номер автомобиля: ", salon.getCatalog().size());
        if (vehicleIndex == -1) {
            return;
        }
        printClients(salon);
        int clientIndex = readIndex(scanner, "Номер клиента: ", salon.getClients().size());
        if (clientIndex == -1) {
            return;
        }

        if (salon.bookTestDrive(vehicleIndex, clientIndex)) {
            System.out.println("Клиент записан на тест-драйв.");
        } else {
            System.out.println("Ошибка: автомобиль уже продан, тест-драйв невозможен.");
        }
    }

    private static void searchVehicles(Scanner scanner, Salon salon) {
        if (salon.getCatalog().isEmpty()) {
            System.out.println("Каталог пуст, искать нечего.");
            return;
        }
        System.out.print("Введите марку или модель: ");
        String text = scanner.nextLine();
        if (text.trim().isEmpty()) {
            System.out.println("Ошибка: строка поиска пустая.");
            return;
        }
        ArrayList<Vehicle> found = salon.search(text);
        if (found.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            printList("Найдено", found);
        }
    }

    private static void filterVehicles(Scanner scanner, Salon salon) {
        if (salon.getCatalog().isEmpty()) {
            System.out.println("Каталог пуст, фильтровать нечего.");
            return;
        }
        System.out.print("Показать машины дешевле (руб.): ");
        double limit;
        try {
            limit = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: сумма должна быть числом.");
            return;
        }
        ArrayList<Vehicle> cheap = salon.findCheaperThan(limit);
        if (cheap.isEmpty()) {
            System.out.println("Нет машин дешевле этой суммы.");
        } else {
            printList("Дешевле " + limit, cheap);
        }
    }

    private static void showStatistics(Salon salon) {
        if (salon.getCatalog().isEmpty()) {
            System.out.println("Каталог пуст, статистики пока нет.");
            return;
        }
        System.out.println("Средняя цена: " + salon.getAveragePrice() + " руб.");
        System.out.println("Самый дорогой: " + salon.getMostExpensive());
        System.out.println("Продано машин: " + salon.getSoldCount());
    }
}