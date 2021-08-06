package com.avangarde.parkinglot.vehicle;

import com.avangarde.parkinglot.vehicle.interfaces.IVehicleAsString;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.util.List;

public class VehiclesAsStringImpl implements IVehicleAsString {
    public VehiclesAsStringImpl() {
    }

    @Override
    public String write(List<Vehicle> vehicles) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n").append("VehicleStart").append("\n");

        for (var vehicle : vehicles)
        {
            if (vehicle != null) {

                stringBuilder.append(vehicle.toString()).append("\n");
            }
        }

        stringBuilder.append("VehicleEnd").append("\n");

        return stringBuilder.toString();
    }
}
