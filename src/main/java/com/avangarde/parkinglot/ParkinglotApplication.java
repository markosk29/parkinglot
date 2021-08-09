package com.avangarde.parkinglot;


import com.avangarde.parkinglot.database.repositories.ParkingLotRepositoryImpl;
import com.avangarde.parkinglot.database.repositories.ParkingSpotRepositoryImpl;
import com.avangarde.parkinglot.database.repositories.VehicleRepositoryImpl;

import java.sql.SQLException;

public class ParkinglotApplication {
    public static void main(String[] args) throws SQLException {
        // 1. READ FILE & PERSIST
        var persist = new PersistLotAndVehiclesFromFile();
        persist.persistDBwithFileInput();
        // 2. LOAD FROM DB
        var parkingLotRepository = new ParkingLotRepositoryImpl();
        var vehiclesRepository = new VehicleRepositoryImpl();
        var parkingSpotRepository = new ParkingSpotRepositoryImpl();
        var latestLot = parkingLotRepository.loadLatestParkingLot();
        var latestVehicles = vehiclesRepository.loadLatestVehicles(latestLot);
        latestLot.summary();
        // 3. PARK & UNPARK VEHICLES
        parkingLotRepository.occupySpotsFromDB(latestVehicles, parkingSpotRepository.getFreeParkingSpotIDsFromDB(), latestLot);
        latestLot.summary();
        var unPark = new UnPark();
        unPark.unparkRandomVehicles();
        // 4. SHOW PARKING LOT SUMMARY
        latestLot.summary();
    }
}
