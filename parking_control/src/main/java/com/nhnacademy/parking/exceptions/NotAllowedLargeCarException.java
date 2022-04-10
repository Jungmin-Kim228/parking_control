package com.nhnacademy.parking.exceptions;

public class NotAllowedLargeCarException extends IllegalArgumentException {
    public NotAllowedLargeCarException(String s) {
        super(s);
    }
}
