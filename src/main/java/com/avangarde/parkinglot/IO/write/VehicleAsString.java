package com.avangarde.parkinglot.IO.write;

import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.util.List;

public interface VehicleAsString {

    String write(List<Vehicle> vehicles);
}
