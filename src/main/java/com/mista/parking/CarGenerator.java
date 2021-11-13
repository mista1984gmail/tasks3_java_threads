package com.mista.parking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

public class CarGenerator implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(CarGenerator.class);

    private Arrival arrival;
    private int carCounter;


    public CarGenerator(Arrival arrival, int carCounter) {
        this.arrival = arrival;
        this.carCounter = carCounter;
    }

    @Override
    public void run() {
        int count =0;
        while (count<carCounter){
            count++;
            Car car = new Car(getRandomName(),System.currentTimeMillis());
            logger.info("{} is waiting",car.getName());

            arrival.add(car);

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            arrival.getCars().stream().forEach(c->arrival.leaveCar(c));

        }
        while (!arrival.getCars().isEmpty()){

            arrival.getCars().stream().forEach(c->arrival.leaveCar(c));

                try{
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

    public Arrival getArrival() {
        return arrival;
    }

    private String getRandomName() {

        String AB = "abcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        int len=3;
        StringBuilder sb = new StringBuilder(len);
        sb.append("car #");

        for( int i = 0; i < len; i++ ){
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        sb.append("-");
        for (int i = 0; i < len; i++) {
            sb.append((int) (Math.random() * 9));
        }
        return sb.toString();
    }
}
