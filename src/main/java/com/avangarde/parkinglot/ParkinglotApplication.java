package com.avangarde.parkinglot;

import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.repositories.VehicleJPARepo;
import com.avangarde.parkinglot.services.ParkingLotService;
import com.avangarde.parkinglot.services.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"com.avangarde.parkinglot.repositories",
        "com.avangarde.parkinglot.services"})
public class ParkinglotApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ParkinglotApplication.class);
        // 1. READ FILE & PERSIST
//        var persist = new PersistLotAndVehiclesFromFile();
//        persist.persistDBwithFileInput();
        // 2. LOAD FROM DB
        var vehicleRepository = context.getBean(VehicleJPARepo.class);
        var parkingLotService = context.getBean(ParkingLotService.class);
        var vehicleService = context.getBean(VehicleService.class);

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

    @Bean
    public EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("com.avangarde.parkinglot");
        return entityManagerFactory.createEntityManager();
    }
}
