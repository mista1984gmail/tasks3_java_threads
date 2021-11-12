package com.mista.port;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Sea {

    private static final Logger logger = LoggerFactory.getLogger(Port.class);


    private ConcurrentLinkedQueue<Ship> ships = null;

    public Sea() {
        ships = new ConcurrentLinkedQueue<Ship>();
    }

    public boolean add(Ship ship){
        return ships.add(ship);
    }

    public Ship get(){
        while (!ships.isEmpty()){
            return ships.poll();
        }
        logger.info("No more ships in sea");
        return null;
    }
}
