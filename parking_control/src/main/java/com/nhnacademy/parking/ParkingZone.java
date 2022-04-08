package com.nhnacademy.parking;

public class ParkingZone {
    private String name;
    private Car car;
    private ParkTime enterTime;
    private ParkTime leaveTime;

    public ParkingZone(String zoneName, Car car, ParkTime enterTime) {
        this.name = zoneName;
        this.car = car;
        this.enterTime = enterTime;
        this.leaveTime = null;
    }

    public String getName() {
        return this.name;
    }

    public Car getCar() {
        return this.car;
    }
}
