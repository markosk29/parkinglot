package com.avangarde.parkinglot.old.database.repositories;

import com.avangarde.parkinglot.old.parking.models.ParkingLot;
import com.avangarde.parkinglot.old.vehicle.models.Vehicle;

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
