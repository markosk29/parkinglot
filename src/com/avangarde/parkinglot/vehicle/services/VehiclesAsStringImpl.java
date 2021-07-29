package com.avangarde.parkinglot.vehicle.services;

import com.avangarde.parkinglot.vehicle.interfaces.IVehicleAsString;
import com.avangarde.parkinglot.vehicle.models.Bike;
import com.avangarde.parkinglot.vehicle.models.Car;
import com.avangarde.parkinglot.vehicle.models.Motorbike;
import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehiclesAsStringImpl implements IVehicleAsString {

    @Override
    public String write(List<Vehicle> vehicles) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n").append("VehicleStart").append("\n");

        for(Vehicle vehicle : vehicles){
            stringBuilder.append(vehicle.toString()).append("\n");
        }

        stringBuilder.append("VehicleEnd").append("\n");

        return stringBuilder.toString();
    }



}
