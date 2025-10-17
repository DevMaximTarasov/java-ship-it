package ru.yandex.practicum.delivery.parcel;

import static constant.FixDeliveryCost.COST_FOR_STANDARD_PER_KG;

public class StandardParcel extends Parcel {
    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    int getFixCost() {
        return COST_FOR_STANDARD_PER_KG;
    }
}
