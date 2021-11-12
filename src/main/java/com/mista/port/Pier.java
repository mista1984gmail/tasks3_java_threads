package com.mista.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pier implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(Port.class);

    Sea sea;
    String name;
    Warehouse warehouse;

    public Pier(Sea sea, String name, Warehouse warehouse) {
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
                Thread.sleep(1000);
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

}
