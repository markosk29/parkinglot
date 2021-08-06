package com.avangarde.parkinglot.IO.read;

import com.avangarde.parkinglot.parking.entities.ParkingLot;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.io.File;
import java.util.List;

public interface ReadFromFile {

    ParkingLot readParkingLot(String path);
    List<Vehicle> readVehicles(String path);
    File generate(ParkingLot parkingLot, String path, String vehicles);
}
