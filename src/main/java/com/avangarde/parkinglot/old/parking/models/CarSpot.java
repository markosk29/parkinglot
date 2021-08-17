package com.avangarde.parkinglot.old.parking.models;

import com.avangarde.parkinglot.old.parking.SpotType;

public class CarSpot extends ParkingSpot {

    public CarSpot() {
        super(false, SpotType.CAR);
    }

    @Override
    public String getInfo() {
        return "This com.avangarde.parkinglot.old.parking spot is for cars.";
    }
}
