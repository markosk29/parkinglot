package com.avangarde.parkinglot.parking.models;

import com.avangarde.parkinglot.vehicle.models.Vehicle;

public class HandicapSpot extends ParkingSpot {

    public HandicapSpot() {
        super(false, SpotType.HANDICAP);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.parking spot is for handicapped.";
    }
}
