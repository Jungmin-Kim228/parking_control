package com.nhnacademy.parking;

import static com.nhnacademy.parking.CarType.COMPACT;
import static com.nhnacademy.parking.CarType.LARGE;
import static com.nhnacademy.parking.Constants.*;

import com.nhnacademy.parking.exceptions.NoMoneyException;
import com.nhnacademy.parking.exceptions.NotAllowedLargeCarException;

public class ParkingService{
    private final ParkingLot parkingLot;

    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void enter(String zoneName, Car car) {
        if (car.getCarType() == LARGE)
            throw new NotAllowedLargeCarException("no large" + car.getCarType());
        ParkingZone zone = new ParkingZone(zoneName, car);
        parkingLot.inputCar(zone);
    }

    public int leave(String zoneName, ParkTime leaveTime) {
        Car car = parkingLot.outputCar(zoneName);
        int fee = calculateFee(getTotalSec(leaveTime));

        if (car.getCarType() == COMPACT)
            fee /= 2;
        if (car.getMoney() < fee)
            throw new NoMoneyException("no money " + car.getMoney());
        else
            car.payMoney(fee);
        return fee;
    }

    public int getTotalSec(ParkTime leaveTime) {
        int totalSec = 0;

        totalSec += leaveTime.getSec();
        totalSec += leaveTime.getMin() * MIN_TO_SEC;
        totalSec += leaveTime.getHour() * HOUR_TO_SEC;
        totalSec += leaveTime.getDay() * DAY_TO_SEC;

        return totalSec;
    }

    public int calculateFee(int totalSec) {
        int fee = 0;
        while (totalSec >= DAY_TO_SEC) {
            fee += FEE_1DAY;
            totalSec -= DAY_TO_SEC;
        }
        if (totalSec >= MAX_DAY_FEE_SEC) {
            return fee + FEE_1DAY;
        }
        if (totalSec <= MIN30_TO_SEC) {
            return fee + FEE_FIRST_30MIN;
        }
        else {
            fee += FEE_FIRST_30MIN;
            totalSec -= MIN30_TO_SEC;
            while (totalSec >= MIN10_TO_SEC) {
                fee += FEE_10MIN;
                totalSec -= MIN10_TO_SEC;
            }
            if (totalSec > 0) {
                fee += FEE_10MIN;
            }
            return fee;
        }
    }
}
