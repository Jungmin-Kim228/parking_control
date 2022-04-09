package com.nhnacademy.parking;

public class ParkingZone {
    private String name;
    private Car car;

    public ParkingZone(String zoneName, Car car) {
        this.name = zoneName;
        this.car = car;
    }

    public String getName() {
        return this.name;
    }

    public Car getCar() {
        return this.car;
    }

}
