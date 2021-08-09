package com.avangarde.parkinglot;



import com.avangarde.parkinglot.database.repositories.ParkingLotRepository;
import com.avangarde.parkinglot.database.repositories.ParkingLotRepositoryImpl;
import com.avangarde.parkinglot.database.repositories.ParkingSpotRepositoryImpl;
import com.avangarde.parkinglot.database.repositories.VehicleRepositoryImpl;
import com.avangarde.parkinglot.parking.entities.ParkingLot;

import java.sql.SQLException;

public class ParkinglotApplication {
    public static void main(String[] args) throws SQLException {
        // 1. READ FILE & PERSIST
        Persist.persistDBwithFileInput();

        // 2. LOAD FROM DB

        // 3. PARK & UNPARK VEHICLES

        // 4. SHOW PARKING LOT SUMMARY

        //Initialize DB repos
        ParkingLotRepositoryImpl parkingLotRepository = new ParkingLotRepositoryImpl();
        ParkingSpotRepositoryImpl parkingSpotRepository = new ParkingSpotRepositoryImpl();
        VehicleRepositoryImpl vehicleRepository = new VehicleRepositoryImpl();

        //Fetch parking lot from DB and store it
        ParkingLot parkingLot = parkingLotRepository.findByIdParkingLot(1);
        parkingLot.summary();

        ParkingLotRepository parkingLotRepository = new ParkingLotRepositoryImpl();
        parkingLotRepository.findByIdParkingLot(1).summary();
        UnPark unPark = new UnPark();
        unPark.unparkRandomVehicles();
        parkingLotRepository.findByIdParkingLot(1).summary();


    }


}
