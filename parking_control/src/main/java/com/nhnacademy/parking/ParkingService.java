package com.nhnacademy.parking;

import com.nhnacademy.parking.exceptions.NoMoneyException;

public class ParkingService {
    private final ParkingLot parkingLot;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public int scan(Car car) {
        return car.getNum();
    }

    public void enter(String name, Car car) {
        parkingLot.inputCar(name, car);
    }

    public int leave(Car car) {
        if (car.getMoney() == 0)
            throw new NoMoneyException("no money" + car.getMoney());
        parkingLot.outCar(car);

        int fee = 100;

        return fee;
    }
}
