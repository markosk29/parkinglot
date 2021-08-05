package com.avangarde.parkinglot.utils;

import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.io.File;
import java.util.List;

public interface IInputFile {

    ParkingLot readParkingLot(String path);
    List<Vehicle> readVehicles(String path);
    File generate(ParkingLot parkingLot, String path, String vehicles);
}
