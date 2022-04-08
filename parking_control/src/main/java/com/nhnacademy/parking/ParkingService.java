package com.nhnacademy.parking;

import com.nhnacademy.parking.exceptions.NoMoneyException;

public class ParkingService {
    private final ParkingLot parkingLot;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void enter(String zoneName, Car car, ParkTime parkTime) {
        ParkingZone zone = new ParkingZone(zoneName, car, parkTime);
        parkingLot.inputCar(zone);
    }

    public void leave(String zoneName) {
        Car car = parkingLot.outCar(zoneName);
        int fee = 1000;

        if (car.getMoney() <= 0)
            throw new NoMoneyException("no money " + car.getMoney());
        else
            car.payMoney(fee);
    }

    private Car getCarInZone(String zoneName) {
        return parkingLot.findCarByZoneName(zoneName);
    }
}
