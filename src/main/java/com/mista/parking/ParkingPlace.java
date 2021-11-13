package com.mista.parking;

import com.mista.port.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class ParkingPlace implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ParkingPlace.class);

    Exchanger<Car> exchanger;

    Arrival arrival;
    String name;
    Semaphore semaphore;

    public ParkingPlace(Exchanger<Car> exchanger, Arrival arrival, String name, Semaphore semaphore) {
        this.exchanger = exchanger;
        this.arrival = arrival;
        this.name = name;
        this.semaphore = semaphore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParkingPlace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                semaphore.acquire();
                Car car = arrival.get();
                if (car == null) {
                    break;
                }
                int timeForParking = timeParking();
                logger.info("Parking place {} accepted {} for time {} sek",
                        getName(),
                        car.name,
                        timeForParking / 1000);

                if (conditionsForExchange()==true){
                    exchangeCar(car);
                }

                Thread.sleep(timeForParking);

                logger.info("Parking place {} is vacated",
                        getName());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int timeParking() {
        return (1 + (int) (Math.random() * 20)) * 1000;
    }

    private boolean exchangeCar(Car car) throws InterruptedException {
        try {
            car=exchanger.exchange(car,5000,TimeUnit.MILLISECONDS);
            logger.info("Cars changed places.");
            logger.info("The car {} is now in place {}",
                    car.name, getName());

        } catch (TimeoutException e) {
        }
        return true;
    }

    public boolean conditionsForExchange() {
        int conditions = 1 + (int) (Math.random() * 4);
        if (conditions==1){
            return true;
        }
        return false;
    }

}

