package com.avangarde.parkinglot.old.IO.randomgeneration;

import com.avangarde.parkinglot.old.IO.read.ReadFromFileImpl;
import com.avangarde.parkinglot.old.IO.write.VehiclesAsStringImpl;
import com.avangarde.parkinglot.old.parking.models.ParkingLot;
import com.avangarde.parkinglot.old.utils.Utils;
//testing Justice League InputGenerator

public class RandomInputFileGeneration {
    public static void main(String[] args) {
        ParkingGenerator iFloorBuilder = new ParkingGenerator();

        ReadFromFileImpl inputFile = new ReadFromFileImpl();

        ParkingLot parkingLot = iFloorBuilder.createParkingLot();

        VehiclesAsStringImpl vehicles = new VehiclesAsStringImpl();

        VehicleGenerator vehicleGenerator = new VehicleGenerator();

        inputFile.generate(parkingLot, Utils.resourcesPath() + "input.txt", vehicles.write(vehicleGenerator.generateVehicleList(parkingLot)));
    }
}
