package com.avangarde.parkinglot.vehicle;

import com.avangarde.parkinglot.vehicle.VehicleType;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

public interface VehicleBuilder {

    Vehicle createVehicle(VehicleType type, String plate);
    Vehicle createVehicle(String type, String plate);
}