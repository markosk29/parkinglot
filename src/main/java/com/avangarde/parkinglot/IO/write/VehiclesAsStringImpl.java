package com.avangarde.parkinglot.IO.write;

import com.avangarde.parkinglot.vehicle.VehicleType;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.util.List;

public class VehiclesAsStringImpl implements VehicleAsString {
    public VehiclesAsStringImpl() {
    }

    @Override
    public String write(List<Vehicle> vehicles) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n").append("VehicleStart").append("\n");

        for (var vehicle : vehicles)
        {
            if (vehicle != null && vehicle.getType() != VehicleType.BIKE) {
                stringBuilder.append(vehicle.getType()).append(" ").append(vehicle.getPlate()).append("\n");
            } else if(vehicle != null) {
                stringBuilder.append(vehicle.getType()).append("\n");
            }
        }

        stringBuilder.append("VehicleEnd").append("\n");

        return stringBuilder.toString();
    }
}
