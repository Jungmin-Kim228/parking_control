package com.nhnacademy.parking;

public class ParkTime {
    private int day;
    private int hour;
    private int min;
    private int sec;

    public ParkTime() {
        this.day = 0;
        this.hour = 0;
        this.min = 0;
        this.sec = 0;
    }

    public ParkTime(int day, int hour, int min, int sec) {
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }
}
