package com.avangarde.parkinglot.parking.entities;

import com.avangarde.parkinglot.parking.SpotType;

public class MotorbikeSpot extends ParkingSpot {

    public MotorbikeSpot() {
        super(false, SpotType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for motorbikes.";
    }
}
