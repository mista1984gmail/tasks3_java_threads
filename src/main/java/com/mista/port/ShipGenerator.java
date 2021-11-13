package com.mista.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

public class ShipGenerator implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ShipGenerator.class);

    Sea sea;
    private int shipCounter;

    public ShipGenerator(Sea sea, int shipCounter) {
        this.sea = sea;
        this.shipCounter = shipCounter;
    }

    @Override
    public void run() {
        int count =0;
        while (count<shipCounter){
            count++;
            Ship ship = new Ship(getRandomName(),getRandomTypeOfShips());
            ship.setLoadingByContainers(getRandomConteiners(ship));
            sea.add(ship);
            logger.info("{} {} with {} containers is waiting",
                    ship.getName(), ship.typeOfShips,ship.getLoadingByContainers());
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private String getRandomName() {

        String AB = "abcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        int len=3;
        StringBuilder sb = new StringBuilder(len);
        sb.append("ship #");

        for( int i = 0; i < len; i++ ){
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        sb.append("-");
        for (int i = 0; i < len; i++) {
            sb.append((int) (Math.random() * 9));
        }
        return sb.toString();
    }

    private TypeOfShips getRandomTypeOfShips(){
        int type = (int) (Math.random() * 3);
        for (TypeOfShips typeOfShips:TypeOfShips.values()) {
            if (typeOfShips.ordinal()==type){
                return typeOfShips;
            }
        }
        return null;
    }

    private int getRandomConteiners(Ship ship) {
        int maxLifting=ship.typeOfShips.getmaximumСapacity();
        return (int)(maxLifting-5+(Math.random() * 6));
    }
}
