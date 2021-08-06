package com.avangarde.parkinglot.database;

import com.avangarde.parkinglot.repositories.*;
import com.avangarde.parkinglot.utils.InputFileImpl;
import com.avangarde.parkinglot.utils.Utils;

public class PersistTest {

   private static final String INPUT_PATH = Utils.resourcesPath() + "input.txt";
   private static ParkingLotRepository parkingLotRepository = new ParkingLotRepositoryImpl();
   private static ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepositoryImpl();
   private static ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepositoryImpl();
   private static VehicleRepository vehicleRepository = new VehicleRepositoryImpl();

    public static void main (String[] args) {
        var inputFile = new InputFileImpl();
        storeParking(inputFile);
        storeVehicles(inputFile);
        // visually check DB
    }

    private static void storeVehicles(InputFileImpl inputFile) {
        var vehicles = inputFile.readVehicles(INPUT_PATH);
        for (var vehicle : vehicles) {
            vehicle.setId(vehicleRepository.createOne(vehicle));
        }
    }

    private static void storeParking(InputFileImpl inputFile) {
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
