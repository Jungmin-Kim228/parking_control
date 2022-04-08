package com.nhnacademy.parking;

public class Car {
    private final String number;
    private int money;


    public Car(String number, int money) {
        this.number = number;
        this.money = money;
    }

    public String getNum() {
        return this.number;
    }

    public int getMoney() {
        return this.money;
    }

    public void payMoney(int money) { // 지불 후 마이너스면 예외처리 가능
        this.money -= money;
    }
}
