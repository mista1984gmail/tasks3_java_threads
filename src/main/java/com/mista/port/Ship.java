package com.mista.port;

public class Ship {
    String name;
    int loadingByContainers;
    TypeOfShips typeOfShips;

    public Ship(String name, TypeOfShips typeOfShips) {
        this.name = name;
        this.typeOfShips = typeOfShips;
    }

    public String getName() {
        return name;
    }

    public int getLoadingByContainers() {
        return loadingByContainers;
    }

    public void setLoadingByContainers(int loadingByContainers) {

        this.loadingByContainers = loadingByContainers;
    }
}

