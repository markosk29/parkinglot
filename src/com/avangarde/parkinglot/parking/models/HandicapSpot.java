package com.avangarde.parkinglot.parking.models;

import com.avangarde.parkinglot.vehicle.models.Vehicle;

public class HandicapSpot extends ParkingSpot {

    public HandicapSpot(Vehicle vehicle) {
        super(false, vehicle, SpotType.HANDICAP);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for handicapped.";
    }
}
