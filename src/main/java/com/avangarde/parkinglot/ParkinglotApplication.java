package com.avangarde.parkinglot;

import com.avangarde.parkinglot.database.repositories.ParkingLotRepository;
import com.avangarde.parkinglot.database.repositories.ParkingLotRepositoryImpl;

import java.sql.SQLException;

public class ParkinglotApplication {
    public static void main(String[] args) throws SQLException {
        // 1. READ FILE & PERSIST
        Persist.persistDBwithFileInput();

        // 2. LOAD FROM DB

        // 3. PARK & UNPARK VEHICLES

        // 4. SHOW PARKING LOT SUMMARY


        ParkingLotRepository parkingLotRepository = new ParkingLotRepositoryImpl();
        parkingLotRepository.findByIdParkingLot(1).summary();
        UnPark unPark = new UnPark();
        unPark.unparkRandomVehicles();
        parkingLotRepository.findByIdParkingLot(1).summary();
    }
}
