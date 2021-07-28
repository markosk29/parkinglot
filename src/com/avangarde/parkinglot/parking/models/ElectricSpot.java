package com.avangarde.parkinglot.parking.models;

import com.avangarde.parkinglot.vehicle.models.Vehicle;

public class ElectricSpot extends ParkingSpot {

    public ElectricSpot(Vehicle vehicle) {
        super(false, vehicle, SpotType.ELECTRIC);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for motorbikes.";
    }
}
