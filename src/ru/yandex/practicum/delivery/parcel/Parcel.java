package ru.yandex.practicum.delivery.parcel;

public abstract class Parcel {
    //добавьте реализацию и другие необходимые классы
    private String description;
    private int weight;
    private String deliveryAddress;
    private int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    @Override
    public String toString() {
        return "Посылка{" +
                "Описание='" + description + '\'' +
                ", вес=" + weight +
                ", адрес='" + deliveryAddress + '\'' +
                ", день отправки=" + sendDay +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public int getSendDay() {
        return sendDay;
    }

    public void setSendDay(int sendDay) {
        this.sendDay = sendDay;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void packageItem() {
        System.out.println("Посылка <<" + this.getDescription() + ">> упакована");
    }

    public void deliver() {
        System.out.println("Посылка <<" + this.getDescription() + ">> доставлена по адресу " + this.getDeliveryAddress());
    }

    public int calculateDeliveryCost() {
        return getWeight() * getFixCost();
    }

    abstract int getFixCost();
}
