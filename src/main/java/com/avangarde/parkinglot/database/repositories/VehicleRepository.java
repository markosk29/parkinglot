package com.avangarde.parkinglot.database.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingLot;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.sql.SQLException;
import java.util.List;


public interface VehicleRepository {
    /**
     * @param vehicle
     * @return generated id of newly inserted row
     */
    int createOne(Vehicle vehicle);

    List<Vehicle> loadLatestVehicles(ParkingLot parkingLot);

    boolean updateParkingSpot(int id, boolean isOccupied);

    Vehicle findByIdVehicle(int id) throws SQLException;
}
