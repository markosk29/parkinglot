package com.avangarde.parkinglot.vehicle.interfaces;

import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.util.List;

public interface IVehicleAsString {

    String write(List<Vehicle> vehicles);
}
