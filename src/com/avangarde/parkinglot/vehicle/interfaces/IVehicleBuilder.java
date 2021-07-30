package com.avangarde.parkinglot.vehicle.interfaces;

import com.avangarde.parkinglot.vehicle.models.Vehicle;
import com.avangarde.parkinglot.vehicle.models.VehicleType;

public interface IVehicleBuilder {

    Vehicle createVehicle(VehicleType type, String plate);
    Vehicle createVehicle(String type, String plate);
}