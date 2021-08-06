package com.avangarde.parkinglot.parking.entities;

import com.avangarde.parkinglot.parking.SpotType;

public class BikeSpot extends ParkingSpot {

    public BikeSpot() {
        super(false, SpotType.BIKE);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for bikes.";
    }
}
