package com.mista.port;

public enum TypeOfShips {

    CONTAINER_SHIP_SMALL(10),
    CONTAINER_SHIP_AVERAGE(20),
    CONTAINER_SHIP_BIG(30);

    private int maximumСapacity;

    TypeOfShips(int maximumСapacity) {
        this.maximumСapacity = maximumСapacity;
    }

    public int getmaximumСapacity() {
        return maximumСapacity;
    }

}
