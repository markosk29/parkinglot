package com.avangarde.parkinglot.parking.models;

public class MotorbikeSpot extends ParkingSpot {

    public MotorbikeSpot() {
        super(false, SpotType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for motorbikes.";
    }
}
