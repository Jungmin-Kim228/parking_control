package com.nhnacademy.parking;

import static com.nhnacademy.parking.CarType.MIDSIZED;

public class Car {
    private final String number;
    private int money;
    private CarType carType;

    public Car(String number, int money) {
        this.number = number;
        this.money = money;
        this.carType = MIDSIZED;
    }

    public Car(String number, int money, CarType carType) {
        this.number = number;
        this.money = money;
        this.carType = carType;
    }

    public String getNum() {
        return this.number;
    }

    public int getMoney() {
        return this.money;
    }

    public void payMoney(int money) {
        this.money -= money;
    }

    public CarType getCarType() {
        return this.carType;
    }
}
