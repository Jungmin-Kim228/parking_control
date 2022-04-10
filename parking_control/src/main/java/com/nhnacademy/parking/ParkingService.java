package com.nhnacademy.parking;

import static com.nhnacademy.parking.Constants.DAY_TO_SEC;
import static com.nhnacademy.parking.Constants.FEE_10MIN;
import static com.nhnacademy.parking.Constants.FEE_1DAY;
import static com.nhnacademy.parking.Constants.FEE_FIRST_30MIN;
import static com.nhnacademy.parking.Constants.HOUR_TO_SEC;
import static com.nhnacademy.parking.Constants.MAX_DAY_FEE_SEC;
import static com.nhnacademy.parking.Constants.MIN10_TO_SEC;
import static com.nhnacademy.parking.Constants.MIN30_TO_SEC;
import static com.nhnacademy.parking.Constants.MIN_TO_SEC;

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

        int fee = calculateFee(getTotalSec(leaveTime));

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
        totalSec += leaveTime.getMin() * MIN_TO_SEC;
        totalSec += leaveTime.getHour() * HOUR_TO_SEC;
        totalSec += leaveTime.getDay() * DAY_TO_SEC;

        return totalSec;
    }

    public int calculateFee(int totalSec) {
        int fee = 0;
        System.out.println("1 totalSec: " + totalSec + " fee: " + fee);
        while (totalSec >= DAY_TO_SEC) {
            fee += FEE_1DAY;
            totalSec -= DAY_TO_SEC;
            System.out.println("2 totalSec: " + totalSec + " fee: " + fee);
        }
        if (totalSec >= MAX_DAY_FEE_SEC) {
            System.out.println("3 totalSec: " + totalSec + " fee: " + fee);
            return fee + FEE_1DAY;
        }
        if (totalSec <= MIN30_TO_SEC) {
            System.out.println("4 totalSec: " + totalSec + " fee: " + fee);
            return fee + FEE_FIRST_30MIN;
        }
        else {
            fee += FEE_FIRST_30MIN;
            totalSec -= MIN30_TO_SEC;
            System.out.println("5 totalSec: " + totalSec + " fee: " + fee);
            while (totalSec >= MIN10_TO_SEC) {
                fee += FEE_10MIN;
                totalSec -= MIN10_TO_SEC;
                System.out.println("6 totalSec: " + totalSec + " fee: " + fee);
            }
            if (totalSec > 0) {
                fee += FEE_10MIN;
                System.out.println("7 totalSec: " + totalSec + " fee: " + fee);
            }
            return fee;
        }
    }
}
