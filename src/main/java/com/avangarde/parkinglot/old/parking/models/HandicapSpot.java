package com.avangarde.parkinglot.old.parking.models;

import com.avangarde.parkinglot.old.parking.SpotType;

public class HandicapSpot extends ParkingSpot {

    public HandicapSpot() {
        super(false, SpotType.HANDICAP);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.old.parking spot is for handicapped.";
    }
}
