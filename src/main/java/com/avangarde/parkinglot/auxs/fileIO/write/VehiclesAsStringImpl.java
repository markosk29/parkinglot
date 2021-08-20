package com.avangarde.parkinglot.auxs.fileIO.write;

import com.avangarde.parkinglot.entities.Vehicle;
import com.avangarde.parkinglot.auxs.intermeds.VehicleType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehiclesAsStringImpl implements VehicleAsString {
    @Override
    public String write(List<Vehicle> vehicles) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n").append("VehicleStart").append("\n");

        for (var vehicle : vehicles)
        {
            if (vehicle != null && vehicle.getVehicleType() != VehicleType.BIKE) {
                stringBuilder.append(vehicle.getVehicleType()).append(" ").append(vehicle.getPlate()).append("\n");
            } else if(vehicle != null) {
                stringBuilder.append(vehicle.getVehicleType()).append("\n");
            }
        }

        stringBuilder.append("VehicleEnd").append("\n");

        return stringBuilder.toString();
    }
}
