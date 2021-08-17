package com.avangarde.parkinglot.old.parking.models;

import com.avangarde.parkinglot.old.parking.SpotType;

public class BikeSpot extends ParkingSpot {

    public BikeSpot() {
        super(false, SpotType.BIKE);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.old.parking spot is for bikes.";
    }
}
