package com.avangarde.parkinglot.parking.entities;

import com.avangarde.parkinglot.parking.SpotType;

public class ElectricSpot extends ParkingSpot {

    public ElectricSpot() {
        super(false, SpotType.ELECTRIC);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for motorbikes.";
    }
}
