package ru.ranepa.carsalon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в автосалон!");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> catalog = new ArrayList<>();

        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine();

            int command;
            try {
                command = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести число от 0 до 2.");
                continue;
            }

            if (command == 1) {
                addCar(scanner, catalog);
            } else if (command == 2) {
                showCatalog(catalog);
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
        System.out.println("0 - Выход");
        System.out.print("Выберите пункт: ");
    }

    private static void addCar(Scanner scanner, ArrayList<String> catalog) {
        System.out.print("Введите название автомобиля: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Название не может быть пустым.");
            return;
        }
        catalog.add(name);
        System.out.println("Автомобиль добавлен.");
    }

    private static void showCatalog(ArrayList<String> catalog) {
        if (catalog.isEmpty()) {
            System.out.println("Каталог пока пуст.");
            return;
        }
        System.out.println("Каталог:");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println((i + 1) + ". " + catalog.get(i));
        }
    }
}