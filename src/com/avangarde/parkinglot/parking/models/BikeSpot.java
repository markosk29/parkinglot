package com.avangarde.parkinglot.parking.models;

import com.avangarde.parkinglot.vehicle.models.Vehicle;

public class BikeSpot extends ParkingSpot {

    public BikeSpot(Vehicle vehicle) {
        super(false, vehicle, SpotType.BIKE);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for bikes.";
    }
}
