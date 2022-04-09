package com.nhnacademy.parking;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<String, ParkingZone> lot = new HashMap<>();

    public int getTotalCar() {
        return this.lot.size();
    }

    public void inputCar(ParkingZone zone) {
        lot.put(zone.getName(), zone);
    }

    public Car outputCar(String zoneName) {
        Car car = findCarByZoneName(zoneName);
        lot.remove(zoneName);
        return car;
    }

    public Car findCarByZoneName(String zoneName) {
        for (String name : lot.keySet()) {
            if (name.equals(zoneName))
                return lot.get(name).getCar();
        }
        return null;
    }

    public ParkingZone findZoneByName(String zoneName) {
        for (String name : lot.keySet()) {
            if (name.equals(zoneName))
                return lot.get(name);
        }
        return null;
    }
}
