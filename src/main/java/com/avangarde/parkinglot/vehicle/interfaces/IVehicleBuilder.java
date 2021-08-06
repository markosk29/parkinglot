package com.avangarde.parkinglot.vehicle.interfaces;

import com.avangarde.parkinglot.vehicle.VehicleType;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

public interface IVehicleBuilder {

    Vehicle createVehicle(VehicleType type, String plate);
    Vehicle createVehicle(String type, String plate);
}