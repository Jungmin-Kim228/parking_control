package com.nhnacademy.parking;

import static com.nhnacademy.parking.Constants.ONE_DAY_TO_SEC;
import static com.nhnacademy.parking.Constants.ONE_HOUR_TO_SEC;
import static com.nhnacademy.parking.Constants.ONE_MIN_TO_SEC;

import com.nhnacademy.parking.exceptions.NoMoneyException;

public class ParkingService {
    private final ParkingLot parkingLot;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void enter(String zoneName, Car car) {
        ParkingZone zone = new ParkingZone(zoneName, car);
        parkingLot.inputCar(zone);
    }

    public int leave(String zoneName, ParkTime leaveTime) {
        Car car = parkingLot.outputCar(zoneName);

        int fee = 1000;

        if (car.getMoney() <= 0)
            throw new NoMoneyException("no money " + car.getMoney());
        else
            car.payMoney(fee);
        return fee;
    }

    private ParkingZone getZoneByName(String zoneName) {
        return parkingLot.findZoneByName(zoneName);
    }

    public int getTotalSec(ParkTime leaveTime) {
        int totalSec = 0;

        totalSec += leaveTime.getSec();
        totalSec += leaveTime.getMin() * ONE_MIN_TO_SEC;
        totalSec += leaveTime.getHour() * ONE_HOUR_TO_SEC;
        totalSec += leaveTime.getDay() * ONE_DAY_TO_SEC;

        return totalSec;
    }

    public int calculateFee(int totalSec) {

        return 0;
    }
}
