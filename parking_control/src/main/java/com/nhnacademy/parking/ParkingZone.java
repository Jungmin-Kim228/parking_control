package com.nhnacademy.parking;

public class ParkingZone {
    private String name;
    private Car car;
    private boolean isParked;

    public ParkingZone(String name, Car car) {
        this.name = name;
        this.car = car;
        isParked = false;
    }

    public String getName() {
        return this.name;
    }

    public Car getCar() {
        return this.car;
    }
}
