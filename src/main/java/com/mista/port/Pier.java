package com.mista.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Pier implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(Port.class);

    Exchanger<Integer> exchanger;
    Exchanger<TypeOfShips> exchangerTOS;

    Sea sea;
    String name;
    Warehouse warehouse;

    public Pier(Exchanger<Integer> exchanger, Exchanger<TypeOfShips> exchangerTOS, Sea sea, String name, Warehouse warehouse) {
        this.exchanger = exchanger;
        this.exchangerTOS = exchangerTOS;
        this.sea = sea;
        this.name = name;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(3000);
                Ship ship = sea.get();
                if (ship == null) {
                    break;
                }
                logger.info("Piers {} accepted {}", name, ship.name);

                Thread.sleep(3000);

                if (overloadedShip(ship)) continue;

                if(warehouse.addContainer(ship)){

                    logger.info("{} unloaded {} containers successfully, in warehouse {} containers",
                            ship.name,ship.getLoadingByContainers(), warehouse.containersInWarehouse());

                    warehouse.getContainer(ship);

                    logger.info("{} loaded {} containers successfully, in warehouse {} containers",
                            ship.name,ship.getLoadingByContainers(), warehouse.containersInWarehouse());

                }
                else {
                    logger.info("Warehouse is full ({} containers) {} is not unloaded left {} pier",
                            warehouse.containersInWarehouse(),ship.name,name);
                }
                Thread.sleep(4000);
                logger.info("Piers {} vacated", name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }}

    private boolean overloadedShip(Ship ship) throws InterruptedException {
        if (ship.typeOfShips==(exchangerTOS.exchange(ship.typeOfShips))){
            try {
                ship.setLoadingByContainers(exchanger.exchange(ship.getLoadingByContainers()
                        ,2000, TimeUnit.MILLISECONDS));
                logger.info("{} was overloaded with {} containers ",
                        ship.name, ship.getLoadingByContainers());
                logger.info("Piers {} vacated", name);
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

}
