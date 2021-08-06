package com.avangarde.parkinglot.vehicle.entities;

import com.avangarde.parkinglot.vehicle.VehicleType;

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
