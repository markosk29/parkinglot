package com.avangarde.parkinglot.old.IO.read;

import com.avangarde.parkinglot.current.entities.Vehicle;
import com.avangarde.parkinglot.old.parking.models.ParkingLot;

import java.io.File;
import java.util.List;

public interface ReadFromFile {

    ParkingLot readParkingLot(String path);
    List<Vehicle> readVehicles(String path);
    File generate(ParkingLot parkingLot, String path, String vehicles);
}
