package com.avangarde.parkinglot.old.vehicle.models;

import com.avangarde.parkinglot.old.vehicle.VehicleType;

public class Car extends Vehicle {

    public Car() {
        super(VehicleType.CAR);
    }

    public Car(String plate) {
        super(VehicleType.CAR);
        this.setPlate(plate);
    }

    @Override
    public String getInfo() {
        return "Car ["  + this.getPlate() + "]";
    }

}
