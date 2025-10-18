package ru.yandex.practicum.delivery.parcel;

public abstract class Parcel {
    //добавьте реализацию и другие необходимые классы
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

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

    public void setSendDay(int sendDay) {
        this.sendDay = sendDay;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void packageItem() {
        System.out.println("Посылка <<" + this.description+ ">> упакована");
    }

    public void deliver() {
        System.out.println("Посылка <<" + this.description + ">> доставлена по адресу " + this.deliveryAddress);
    }

    public int calculateDeliveryCost() {
        return weight * getFixCost();
    }

    abstract int getFixCost();
}
