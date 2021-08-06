package com.avangarde.parkinglot.vehicle;

import com.avangarde.parkinglot.vehicle.entities.*;

public class VehicleFactory {

    public static Vehicle createVehicle(VehicleType type, String plate){

        switch(type) {
            case CAR:
                return new Car(plate);
            case BIKE:
                return new Bike();
            case MOTORBIKE:
                return new Motorbike(plate);
        }
        return null;
    }

    public static Vehicle createVehicle(String type, String plate){
        VehicleType vehicleType;

        try{
            vehicleType = VehicleType.valueOf(type);
        } catch(IllegalArgumentException e) {
            return null;
        }

        switch(vehicleType) {
            case CAR:
                return new Car(plate);
            case BIKE:
                return new Bike();
            case MOTORBIKE:
                return new Motorbike(plate);
        }

        return null;
    }

}
