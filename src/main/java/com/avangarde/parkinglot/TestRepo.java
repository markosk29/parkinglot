package com.avangarde.parkinglot;

import com.avangarde.parkinglot.database.repositories.*;
import com.avangarde.parkinglot.parking.entities.ParkingFloor;
import com.avangarde.parkinglot.parking.entities.ParkingLot;

import java.sql.SQLException;

public class TestRepo {
    public static void main(String[] args) throws SQLException {
        //testing ParkingSpotRepository
        ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepositoryImpl();
        System.out.println(parkingSpotRepository.findByIdParkingSpot(2) + "\n");

        //testing VehicleRepository
        VehicleRepository vehicleRepository = new VehicleRepositoryImpl();
        System.out.println(vehicleRepository.findByIdVehicle(2));

        //testing ParkingFloorRepository
        ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepositoryImpl();
        ParkingFloor parkingFloor = parkingFloorRepository.findByIdParkingFloor(2);
        System.out.println(parkingFloor.getFreeSpotsSummary() + "\n");

        //testing ParkingLotRepository
        ParkingLotRepository parkingLotRepository = new ParkingLotRepositoryImpl();
        ParkingLot parkingLot = parkingLotRepository.findByIdParkingLot(1);
        parkingLot.summary();
    }
}
