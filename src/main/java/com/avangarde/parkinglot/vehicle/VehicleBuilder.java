package com.avangarde.parkinglot.vehicle;

import com.avangarde.parkinglot.vehicle.interfaces.IVehicleBuilder;
import com.avangarde.parkinglot.vehicle.entities.*;

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
        return new VehicleFactory().createVehicle(type, plate);
    }

    @Override
    public Vehicle createVehicle(String type, String plate) {
        return new VehicleFactory().createVehicle(type, plate);
    }
}
