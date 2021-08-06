package com.avangarde.parkinglot.vehicle;

import com.avangarde.parkinglot.vehicle.entities.*;

public class VehicleBuilderImpl implements VehicleBuilder {
    private VehicleType type;
    private String plate;

    public static VehicleBuilderImpl builder() {
        return new VehicleBuilderImpl();
    }

    public VehicleBuilderImpl type(VehicleType type) {
        this.type = type;
        return this;
    }

    public VehicleBuilderImpl plate(String plate) {
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
