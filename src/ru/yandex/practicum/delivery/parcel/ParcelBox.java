package ru.yandex.practicum.delivery.parcel;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private ArrayList<T> parcel = new ArrayList<>();
    private final int maxWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public ArrayList<T> getAllParcels() {
        return parcel;
    }

    public void addParcel(T newParcel) {
        if (newParcel.getWeight() >= this.maxWeight) {
            System.out.println("Вес посылки больше допустимого максимума");
        } else {
            this.parcel.add(newParcel);
        }
    }

    public void printAllParcels() {
        for (T parcel : parcel) {
            System.out.println("Список всех посылок в коробке: ");
            System.out.println(parcel.toString());
        }
    }
}
