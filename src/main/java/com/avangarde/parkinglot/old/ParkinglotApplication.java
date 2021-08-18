package com.avangarde.parkinglot.old;

import com.avangarde.parkinglot.current.repositories.VehicleJPARepo;
import com.avangarde.parkinglot.current.services.ParkingLotService;
import com.avangarde.parkinglot.current.services.VehicleService;

import java.sql.SQLException;

public class ParkinglotApplication {
    public static void main(String[] args) throws SQLException {
        // 1. READ FILE & PERSIST
        var persist = new PersistLotAndVehiclesFromFile();
        persist.persistDBwithFileInput();
        // 2. LOAD FROM DB
        var vehicleRepository = new VehicleJPARepo();
        var parkingLotService = new ParkingLotService();
        var vehicleService = new VehicleService();
        var latestLot = parkingLotService.loadLatestParkingLot();
        var latestVehicles = vehicleRepository.readAll();
        parkingLotService.summary(latestLot);
        // 3. PARK & UNPARK VEHICLES
        for (var vehicle : latestVehicles) {
            vehicleService.park(vehicle);
        }
//        parkingLotRepository.occupySpots(latestVehicles, parkingSpotRepository.getFreeParkingSpotIDs(latestLot.getId()), latestLot);
        parkingLotService.summary(latestLot);
        for (var vehicle : latestVehicles) {
            vehicleService.unpark(vehicle);
        }
//        var unPark = new UnPark();
//        unPark.unparkRandomVehicles();
        // 4. SHOW PARKING LOT SUMMARY
        parkingLotService.summary(latestLot);
    }

}
