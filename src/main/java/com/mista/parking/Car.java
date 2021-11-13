package com.mista.parking;

public class Car {
    String name;
    long timeWaiting;

    public Car(String name, long timeWaiting) {
        this.name = name;
        this.timeWaiting = timeWaiting;
    }

    public String getName() {
        return name;
    }
}
