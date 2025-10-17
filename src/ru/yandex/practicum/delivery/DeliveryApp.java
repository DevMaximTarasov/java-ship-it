package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.parcel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> allTrackables = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(150);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(200);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.next());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 0:
                    running = false;
                    break;
                case 4:
                    setNewTrackableParcelsLocation();
                    break;
                case 5:
                    showInsideOfBox();
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Изменить местоположение посылки с трекингом");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        int command = readCommandParcelType();
        System.out.println("Введите данные для посылки.");
        String description = readDescription();
        int weight = readWeight();
        String address = readAddress();
        int sendDay = readSendDay();

        switch (command) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, address, sendDay);
                standardParcelBox.addParcel(standardParcel);
                allParcels.add(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, address, sendDay);
                allTrackables.add(fragileParcel);
                fragileParcelBox.addParcel(fragileParcel);
                allParcels.add(fragileParcel);
                break;
            case 3:
                int timeToLive = readTimeToLive();
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
                perishableParcelBox.addParcel(perishableParcel);
                allParcels.add(perishableParcel);
                break;
        }
    }

    private static int readTimeToLive() {
        int timeToLive;
        while (true) {
            System.out.println("Срок в днях, за который посылка не испортится: ");
            timeToLive = scanner.nextInt();
            if (timeToLive <= 0) {
                System.out.println("Срок в днях не может быть меньше нуля");
                continue;
            }
            break;
        }
        return timeToLive;
    }

    private static int readSendDay() {
        int sendDay;
        while (true) {
            System.out.println("День месяца, в который посылка была отправлена: ");
            sendDay = scanner.nextInt();
            if (sendDay < 1 || sendDay > 31) {
                System.out.println("День месяца должен быть в интервале 1-31");
                continue;
            }
            break;
        }
        return sendDay;
    }

    private static String readAddress() {
        String address;
        while (true) {
            System.out.println("Адрес места назначения посылки: ");
            address = scanner.next();
            if (address.isEmpty()) {
                System.out.println("Адрес не может быть пустым");
                continue;
            }
            break;
        }
        return address;
    }

    private static int readWeight() {
        int weight;
        while (true) {
            System.out.println("Вес: ");
            weight = scanner.nextInt();
            if (weight < 1 || weight > 300) {
                System.out.println("Вес должен быть больше 1");
                continue;
            }
            break;
        }
        return weight;
    }

    private static String readDescription() {
        String description;
        while (true) {
            System.out.println("Краткое описание: ");
            description = scanner.next();
            if (description.isEmpty()) {
                System.out.println("Краткое описание посылки должно быть заполнено обязательно");
                continue;
            }
            break;
        }
        return description;
    }

    private static int readCommandParcelType() {
        int command;
        while (true) {
            System.out.println("Введите номер типа посылки: ");
            System.out.println("1 — Стандартная посылка");
            System.out.println("2 — Хрупкая посылка");
            System.out.println("3 — Скоропортящаяся посылка");
            command = scanner.nextInt();
            if (command > 3 || command < 0) {
                System.out.println("Введен некорректный номер для типа посылки: " + command);
                continue;
            }
            break;
        }
        return command;
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + totalCost + " рублей.");
    }

    private static void showInsideOfBox() {
        int command;
        while (true) {
            System.out.println("Введите номер типа коробки: ");
            System.out.println("1 — Коробка со стандартной посылкой");
            System.out.println("2 — Коробка с хрупкой посылкой");
            System.out.println("3 — Коробка со скоропортящейся посылкой");
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Содержимое: ");
                    standardParcelBox.printAllParcels();
                    break;
                case 2:
                    System.out.println("Содержимое: ");
                    fragileParcelBox.printAllParcels();
                    break;
                case 3:
                    System.out.println("Содержимое: ");
                    perishableParcelBox.printAllParcels();
                    break;
                default:
                    System.out.println("Введен некорректный номер для типа коробки: " + command);
                    continue;
            }
            break;
        }
    }

    private static void setNewTrackableParcelsLocation() {
        if (allTrackables.isEmpty()) {
            System.out.println("Хрупкие посылки отсутствуют");
            return;
        }

        System.out.println("Введите новый адрес: ");
        String newLocation = scanner.next();

        for (Trackable trackable : allTrackables) {
            trackable.reportStatus(newLocation);
        }
    }
}

