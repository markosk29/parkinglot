package com.avangarde.parkinglot.auxs.fileIO.randomgeneration;

import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.auxs.fileIO.read.ReadFromFileImpl;
import com.avangarde.parkinglot.auxs.fileIO.write.VehiclesAsStringImpl;
import com.avangarde.parkinglot.auxs.fileIO.Utils;
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
