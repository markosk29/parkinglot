package com.avangarde.parkinglot.InputGenerator;

import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.utils.InputFileImpl;
import com.avangarde.parkinglot.utils.Utils;
import com.avangarde.parkinglot.vehicle.services.VehiclesAsStringImpl;

import java.nio.file.Paths;
//testing Justice League InputGenerator

public class TestMainJL {
    public static void main(String[] args) {
        IFloorBuilderImpl iFloorBuilder = new IFloorBuilderImpl();

        InputFileImpl inputFile = new InputFileImpl();

        ParkingLot parkingLot = iFloorBuilder.createParkingLot();

        VehiclesAsStringImpl vehicles = new VehiclesAsStringImpl();

        VehicleGenerator vehicleGenerator = new VehicleGenerator();

        inputFile.generate(parkingLot,  Utils.resourcesPath() + "input.txt", vehicles.write(vehicleGenerator.generateVehicleList(parkingLot)));
    }
}
