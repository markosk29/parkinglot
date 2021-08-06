package com.avangarde.parkinglot;

import com.avangarde.parkinglot.database.repositories.*;
import com.avangarde.parkinglot.IO.read.ReadFromFileImpl;
import com.avangarde.parkinglot.utils.Utils;

public class Persist {

   private static final String INPUT_PATH = Utils.resourcesPath() + "input.txt";
   private static ParkingLotRepository parkingLotRepository = new ParkingLotRepositoryImpl();
   private static ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepositoryImpl();
   private static ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepositoryImpl();
   private static VehicleRepository vehicleRepository = new VehicleRepositoryImpl();

    public static void main (String[] args) { // PersistTest
        persistDBwithFileInput();
        // visually check DB
    }

    public static void persistDBwithFileInput() {
        var inputFile = new ReadFromFileImpl();
        storeParking(inputFile);
        storeVehicles(inputFile);
    }

    private static void storeVehicles(ReadFromFileImpl inputFile) {
        var vehicles = inputFile.readVehicles(INPUT_PATH);
        for (var vehicle : vehicles) {
            vehicle.setId(vehicleRepository.createOne(vehicle));
        }
    }

    private static void storeParking(ReadFromFileImpl inputFile) {
        // read from file
        var lot = inputFile.readParkingLot(INPUT_PATH); // also creates dependencies (floors, spots)
        // store
        lot.setId(parkingLotRepository.createOne(lot));
        for (var floor : lot.getFloors()) {
            floor.setId(parkingFloorRepository.createOne(floor, lot.getId()));
            floor.setParkingLotId(lot.getId());
            for (var spotPair : floor.getSpotPairs().entrySet()) {
                for (var spot : spotPair.getValue()) {
                    spot.setId(parkingSpotRepository.createOne(spot, floor.getId()));
                    spot.setParkingFloorId(floor.getId());
                }
            }
        }
    }
}
