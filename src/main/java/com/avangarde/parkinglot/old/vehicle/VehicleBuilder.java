package com.avangarde.parkinglot.old.vehicle;

import com.avangarde.parkinglot.old.vehicle.models.Vehicle;

public interface VehicleBuilder {

    Vehicle createVehicle(VehicleType type, String plate);
    Vehicle createVehicle(String type, String plate);
}