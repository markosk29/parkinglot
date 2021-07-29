package com.avangarde.parkinglot.vehicle.models;

public class Car extends Vehicle {

    private String plate;

    public Car() {
        super(VehicleType.CAR);
    }

    @Override
    public String getInfo() {
        return "This is a car.";
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
