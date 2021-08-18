package com.avangarde.parkinglot;

import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.repositories.VehicleJPARepo;
import com.avangarde.parkinglot.services.ParkingLotService;
import com.avangarde.parkinglot.services.VehicleService;

public class ParkinglotApplication {
    public static void main(String[] args) {
        // 1. READ FILE & PERSIST
//        var persist = new PersistLotAndVehiclesFromFile();
//        persist.persistDBwithFileInput();
        // 2. LOAD FROM DB
        var vehicleRepository = new VehicleJPARepo();
        var parkingLotService = new ParkingLotService();
        var vehicleService = new VehicleService();
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
