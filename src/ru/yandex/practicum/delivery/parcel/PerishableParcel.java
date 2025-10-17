package ru.yandex.practicum.delivery.parcel;

import static constant.FixDeliveryCost.COST_FOR_PERISHABLE_PER_KG;

//скоропортящаяся посылка
public class PerishableParcel extends Parcel {
    private int timeToLive;

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return this.getSendDay() + this.timeToLive < currentDay;
    }

    @Override
    int getFixCost() {
        return COST_FOR_PERISHABLE_PER_KG;
    }

    @Override
    public String toString() {
        return "Посылка{" +
                "Описание='" + getDescription() + '\'' +
                ", вес=" + getWeight() +
                ", адрес='" + getDeliveryAddress() + '\'' +
                ", день отправки=" + getSendDay() +
                ", срок годности в днях=" + timeToLive +
                '}';
    }
}
