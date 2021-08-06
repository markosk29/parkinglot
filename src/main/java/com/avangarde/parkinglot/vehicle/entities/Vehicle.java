package com.avangarde.parkinglot.vehicle.entities;

import com.avangarde.parkinglot.database.Identity;
import com.avangarde.parkinglot.vehicle.VehicleType;

public abstract class Vehicle extends Identity {
    private VehicleType type;
    private String plate;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public abstract String getInfo();
}
