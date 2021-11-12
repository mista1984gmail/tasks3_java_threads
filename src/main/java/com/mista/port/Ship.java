package com.mista.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Ship{

    private static final Logger logger = LoggerFactory.getLogger(Port.class);

    String name;
    Integer loadingByContainers;
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

