package com.avangarde.parkinglot.parking.models;

import com.avangarde.parkinglot.vehicle.models.Vehicle;

public class CarSpot extends ParkingSpot {

    public CarSpot(Vehicle vehicle) {
        super(false, vehicle, SpotType.CAR);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for cars.";
    }
}
