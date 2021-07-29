package com.avangarde.parkinglot.vehicle.models;

public class Motorbike extends Vehicle {
    private String plate;

    public Motorbike() {
        super(VehicleType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This is a motorbike";
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "MOTORBIKE " + this.plate;
    }
}
