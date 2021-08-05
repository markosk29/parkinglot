package com.avangarde.parkinglot.vehicle.models;

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
