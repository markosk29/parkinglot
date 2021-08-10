package com.avangarde.parkinglot;

import com.avangarde.parkinglot.database.repositories.*;
import com.avangarde.parkinglot.IO.read.ReadFromFileImpl;
import com.avangarde.parkinglot.utils.Utils;

public class PersistLotAndVehiclesFromFile {

   private static final String INPUT_PATH = Utils.resourcesPath() + "input.txt";
   private final ParkingLotRepository parkingLotRepository = new ParkingLotRepositoryImpl();
   private final ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepositoryImpl();
   private final ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepositoryImpl();
   private final VehicleRepository vehicleRepository = new VehicleRepositoryImpl();

    public static void main (String[] args) { // PersistTest
        var persist = new PersistLotAndVehiclesFromFile();
        persist.persistDBwithFileInput();
        // visually check DB
    }

    public void persistDBwithFileInput() {
        var inputFile = new ReadFromFileImpl();
        storeParking(inputFile);
        storeVehicles(inputFile);
    }

    private void storeVehicles(ReadFromFileImpl inputFile) {
        System.out.println("Storing parking spots into DB...");
        var vehicles = inputFile.readVehicles(INPUT_PATH);
        for (var vehicle : vehicles) {
            vehicle.setId(vehicleRepository.createOne(vehicle));
        }

        System.out.println("Added " +vehicles.size()+ " vehicles into DB.");
    }

    private void storeParking(ReadFromFileImpl inputFile) {
        System.out.println("Storing parking spots into DB...");
        int numberOfSpots = 0;
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

                    numberOfSpots++;
                }
            }
        }

        System.out.println("Added " +numberOfSpots+ " parking spots to DB.");
    }
}
