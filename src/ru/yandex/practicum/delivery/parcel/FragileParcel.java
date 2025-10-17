package ru.yandex.practicum.delivery.parcel;

import ru.yandex.practicum.delivery.Trackable;

import static constant.FixDeliveryCost.COST_FOR_FRAGILE_PER_KG;

//хрупкая посылка
public class FragileParcel extends Parcel implements Trackable {
    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem(){
        System.out.println("Посылка <<" + this.getDescription() +">> обёрнута в защитную плёнку");
    }

    @Override
    int getFixCost() {
        return COST_FOR_FRAGILE_PER_KG;
    }

    @Override
    public void reportStatus(String newLocation) {
        this.setDeliveryAddress(newLocation);
        System.out.printf("Хрупкая посылка <<'%s'>> изменила местоположение на '%s'%n", this.getDescription(), newLocation);
    }
}
