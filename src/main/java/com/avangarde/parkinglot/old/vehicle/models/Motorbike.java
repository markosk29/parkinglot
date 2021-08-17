package com.avangarde.parkinglot.old.vehicle.models;

import com.avangarde.parkinglot.old.vehicle.VehicleType;

public class Motorbike extends Vehicle {

    public Motorbike() {
        super(VehicleType.MOTORBIKE);
    }

    public Motorbike(String plate) {
        super(VehicleType.MOTORBIKE);
        this.setPlate(plate);
    }
    @Override
    public String getInfo() {
        return "Motorbike [" + this.getPlate() + "]";
    }
}
