package com.avangarde.parkinglot.old.parking.models;

import com.avangarde.parkinglot.old.parking.SpotType;

public class MotorbikeSpot extends ParkingSpot {

    public MotorbikeSpot() {
        super(false, SpotType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for motorbikes.";
    }
}
