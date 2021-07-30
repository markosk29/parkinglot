package com.avangarde.parkinglot.vehicle.services;

import com.avangarde.parkinglot.vehicle.interfaces.IVehicleBuilder;
import com.avangarde.parkinglot.vehicle.models.*;

public class VehicleBuilder implements IVehicleBuilder {
    private VehicleType type;
    private String plate;

    public static VehicleBuilder builder() {
        return new VehicleBuilder();
    }

    public VehicleBuilder type(VehicleType type) {
        this.type = type;
        return this;
    }

    public VehicleBuilder plate(String plate) {
        this.plate = plate;
        return this;
    }

    @Override
    public Vehicle createVehicle(VehicleType type, String plate) {
        switch(type) {
            case CAR:
                return new Car();
            case BIKE:
                return new Bike();
            case MOTORBIKE:
                return new Motorbike();
        }

        return null;
    }
}
