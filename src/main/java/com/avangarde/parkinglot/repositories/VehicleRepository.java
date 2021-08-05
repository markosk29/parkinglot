package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> loadLatestVehicles(ParkingLot parkingLot);
    boolean updateParkingSpot(int id, boolean isOccupied);
}
