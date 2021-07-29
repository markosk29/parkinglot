package com.avangarde.parkinglot.vehicle.models;

public abstract class Vehicle {
    private VehicleType type;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public abstract String getInfo();

    @Override
    public String toString() {
        return "Vehicle{" +
                "type=" + type +
                '}';
    }
}
