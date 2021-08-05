package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> loadAllVehicles();
    boolean updateParkingSpot(int id, boolean isOccupied);
}
