package com.avangarde.parkinglot;

import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.repositories.VehicleJPARepo;
import com.avangarde.parkinglot.services.ParkingLotService;
import com.avangarde.parkinglot.services.VehicleService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ParkinglotApplication {
    public static void main(String[] args) {
        // 1. READ FILE & PERSIST
//        var persist = new PersistLotAndVehiclesFromFile();
//        persist.persistDBwithFileInput();
        // 2. LOAD FROM DB
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        var vehicleRepository = ctx.getBean(VehicleJPARepo.class);
        var parkingLotService = ctx.getBean(ParkingLotService.class);
        var vehicleService = ctx.getBean(VehicleService.class);

        ParkingLot latestLot = parkingLotService.loadLatestParkingLot();
        var latestVehicles = vehicleRepository.readAll();
        parkingLotService.summary(latestLot);
        // 3. PARK & UNPARK VEHICLES
        for (var vehicle : latestVehicles) {
            vehicleService.park(vehicle);
        }
        parkingLotService.summary(latestLot);
        for (var vehicle : latestVehicles) {
            vehicleService.unpark(vehicle);
        }
        // 4. SHOW PARKING LOT SUMMARY
        parkingLotService.summary(latestLot);
    }
}
