package com.avangarde.parkinglot.parking.models;

public class ParkingSpotFactory {
    public ParkingSpot createParkingSpot(SpotType type) {
        switch (type) {
            case CAR:
                return new CarSpot();
            case BIKE:
                return new BikeSpot();
            case ELECTRIC:
                return new ElectricSpot();
            case HANDICAP:
                return new HandicapSpot();
            case MOTORBIKE:
                return new MotorbikeSpot();
            default:
//                TODO: Throw exception
                return null;
        }
    }
}
