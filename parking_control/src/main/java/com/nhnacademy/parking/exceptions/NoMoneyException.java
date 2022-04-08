package com.nhnacademy.parking.exceptions;

public class NoMoneyException extends IllegalArgumentException {
    public NoMoneyException(String s) {
        super(s);
    }
}
