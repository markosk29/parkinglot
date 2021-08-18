package com.avangarde.parkinglot.old;

import java.sql.SQLException;

import com.avangarde.parkinglot.current.repositories.VehicleRepository;
import com.avangarde.parkinglot.old.database.repositories.*;

public class ParkinglotApplication {
    public static void main(String[] args) throws SQLException {
        // 1. READ FILE & PERSIST
        var persist = new PersistLotAndVehiclesFromFile();
        persist.persistDBwithFileInput();
        // 2. LOAD FROM DB
        var parkingLotRepository = new ParkingLotRepositoryImpl();
        VehicleRepository vehicleRepository = new VehicleRepository();
        var parkingSpotRepository = new ParkingSpotRepositoryImpl();
        var latestLot = parkingLotRepository.loadLatestParkingLot();
        var latestVehicles = vehicleRepository.readAll();
        latestLot.summary();
        // 3. PARK & UNPARK VEHICLES
        parkingLotRepository.occupySpots(latestVehicles, parkingSpotRepository.getFreeParkingSpotIDs(latestLot.getId()), latestLot);
        latestLot.summary();
        var unPark = new UnPark();
        unPark.unparkRandomVehicles();
        // 4. SHOW PARKING LOT SUMMARY
        latestLot.summary();
    }

}
