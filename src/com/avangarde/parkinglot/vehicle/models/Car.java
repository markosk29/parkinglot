package com.avangarde.parkinglot.vehicle.models;

public class Car extends Vehicle {

    private String plate;

    public Car() {
        super(VehicleType.CAR);
    }

    public Car(String plate) {
        super(VehicleType.CAR);
        this.plate = plate;
    }

    @Override
    public String getInfo() {
        return "Car [" +this.plate+"]";
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "CAR " + this.plate;
    }

}
