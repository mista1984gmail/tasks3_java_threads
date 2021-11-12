package com.mista.port;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private AtomicInteger containers = new AtomicInteger();
    private int maximumContainers;
    private ReentrantLock lock = new ReentrantLock();

    public int getMaximumContainers() {
        return maximumContainers;
    }

    public Warehouse(int containers, int maximumContainers) {
        this.containers.set(containers);
        this.maximumContainers = maximumContainers;
    }

    public synchronized boolean addContainer(Ship ship) throws InterruptedException {
        if (containers.get() < getMaximumContainers()) {

            Thread.sleep((1 + (int) (Math.random() * 10))*1000);
            containers.getAndAdd(ship.getLoadingByContainers());

            return true;
        }
        else
            return false;
    }

    public boolean getContainer(Ship ship) throws InterruptedException {
        int maxLifting=ship.typeOfShips.getmaximumСapacity();
        if (containers.get() > 0) {
            Thread.sleep((1 + (int) (Math.random() * 10))*1000);
            ship.setLoadingByContainers((int)(maxLifting-5+(Math.random() * 6)));
            if (ship.getLoadingByContainers()>containers.get()){
                ship.setLoadingByContainers(containers.get());
            }
            containers.lazySet(containers.get()-ship.getLoadingByContainers());
            return true;
        }
        else
            return false;
    }

    public int containersInWarehouse() {
        return containers.get();
    }
}
