package com.avangarde.parkinglot.parking.models;

public class ElectricSpot extends ParkingSpot {

    public ElectricSpot() {
        super(false, SpotType.ELECTRIC);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for motorbikes.";
    }
}
