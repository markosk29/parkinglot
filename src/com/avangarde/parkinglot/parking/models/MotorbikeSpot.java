package com.avangarde.parkinglot.parking.models;

import com.avangarde.parkinglot.vehicle.models.Vehicle;

public class MotorbikeSpot extends ParkingSpot {

    public MotorbikeSpot(Vehicle vehicle) {
        super(false, vehicle, SpotType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for motorbikes.";
    }
}
