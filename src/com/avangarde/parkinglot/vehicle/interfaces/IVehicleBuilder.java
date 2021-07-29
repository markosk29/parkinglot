package com.avangarde.parkinglot.vehicle.interfaces;

import com.avangarde.parkinglot.vehicle.models.Vehicle;
import com.avangarde.parkinglot.vehicle.models.VehicleType;

public interface IVehicleBuilder {

    Vehicle createVehile(VehicleType type, String plate);
}