package com.avangarde.parkinglot.old.vehicle.models;

import com.avangarde.parkinglot.old.vehicle.VehicleType;

public class Bike extends Vehicle {
    public Bike() {
        super(VehicleType.BIKE);
    }

    @Override
    public String getInfo() {
        return "Bike [NO PLATE].";
    }


    @Override
    public String toString() {
        return "BIKE";
    }


}
