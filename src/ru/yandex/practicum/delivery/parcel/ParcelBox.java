package ru.yandex.practicum.delivery.parcel;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private ArrayList<T> parcels = new ArrayList<>();
    private final int maxWeight;
    private int totalWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public ArrayList<T> getAllParcels() {
        return parcels;
    }

    public void addParcel(T newParcel) {
        if (newParcel.getWeight() + totalWeight > this.maxWeight) {
            System.out.printf("Нельзя добавить посылку, тк оставшийся вес, который может поместиться в коробку = %s, а вес посылки = %s %n",
                    this.maxWeight - totalWeight, newParcel.getWeight());
        } else {
            this.parcels.add(newParcel);
            totalWeight += newParcel.getWeight();
        }
    }

    public void printAllParcels() {
        System.out.println("Список всех посылок в коробке: ");
        for (T parcel : parcels) {
            System.out.println(parcel.toString());
        }
    }
}
