package com.mista.parking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Arrival {

    private static final Logger logger = LoggerFactory.getLogger(Arrival.class);

    private ConcurrentLinkedDeque<Car> cars = null;

    public ConcurrentLinkedDeque<Car> getCars() {
        return cars;
    }

    public Arrival() {
        cars=new ConcurrentLinkedDeque<Car>();
    }

    public boolean add(Car car){
        return cars.offerLast(car);
    }

    public Car get(){
        return cars.pollFirst();
    }

    public void leaveCar(Car car){

        long start=car.timeWaiting;
        long end=System.currentTimeMillis();

        if (((end-start)/1000)>20){
            cars.remove(car);
            logger.info("{} leave", car.getName());
        }
    }
}
