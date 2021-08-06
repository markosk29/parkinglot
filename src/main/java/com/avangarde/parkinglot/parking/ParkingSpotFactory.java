package com.avangarde.parkinglot.parking;

import com.avangarde.parkinglot.parking.SpotType;
import com.avangarde.parkinglot.parking.entities.*;

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

    public ParkingSpot createParkingSpot(String type) {
        SpotType spotType;

        try {
            spotType = SpotType.valueOf(type);
        } catch(IllegalArgumentException e) {
            return null;
        }

        switch (spotType) {
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
                return null;
        }
    }
}
