package com.mista.parking;

import org.apache.log4j.PropertyConfigurator;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Parking {
    public static void main(String[] args) {
        PropertyConfigurator.configure("D:\\Project\\epam_course\\home_work_three\\tasks3_java_threads\\src\\log4j.properties");

        Arrival arrival = new Arrival();

        Exchanger<Car> exchanger = new Exchanger<>();

        CarGenerator carGenerator = new CarGenerator(arrival, 12);

        Semaphore semaphore = new Semaphore(3);

         ParkingPlace parkingPlace1 = new ParkingPlace(exchanger, arrival,"1",semaphore);

         ParkingPlace parkingPlace2 = new ParkingPlace(exchanger,arrival,"2",semaphore);

         ParkingPlace parkingPlace3 = new ParkingPlace(exchanger, arrival,"3",semaphore);


        ExecutorService service = Executors.newFixedThreadPool(5);

        service.execute(carGenerator);
        service.execute(parkingPlace1);
        service.execute(parkingPlace2);
        service.execute(parkingPlace3);

        service.shutdown();
}}
