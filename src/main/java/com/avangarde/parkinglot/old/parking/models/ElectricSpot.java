package com.avangarde.parkinglot.old.parking.models;

import com.avangarde.parkinglot.old.parking.SpotType;

public class ElectricSpot extends ParkingSpot {

    public ElectricSpot() {
        super(false, SpotType.ELECTRIC);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.old.parking spot is for motorbikes.";
    }
}
