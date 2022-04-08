package com.nhnacademy.parking;

public class Car {
    private int num;
    private int money;

    public Car(int num, int money) {
        this.num = num;
        this.money = money;
    }

    public int getNum() {
        return this.num;
    }

    public int getMoney() {
        return this.money;
    }
}
