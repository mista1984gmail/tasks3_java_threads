package com.mista.port;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Port {
    private static final Logger logger = LoggerFactory.getLogger(Port.class);
    public static void main(String[] args) {
        PropertyConfigurator.configure("D:\\Project\\epam_course\\home_work_three\\tasks3_java_threads\\src\\log4j.properties");

        Sea sea = new Sea();
        ShipGenerator shipGenerator = new ShipGenerator(sea,10);
        Warehouse warehouse=new Warehouse(100,250);
        Pier pier1 = new Pier(sea,"1",warehouse);
        Pier pier2 = new Pier(sea,"2",warehouse);
        Pier pier3 = new Pier(sea,"3",warehouse);
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(shipGenerator);
        service.execute(pier1);
        service.execute(pier2);
        service.execute(pier3);
        service.shutdown();
    }
}
