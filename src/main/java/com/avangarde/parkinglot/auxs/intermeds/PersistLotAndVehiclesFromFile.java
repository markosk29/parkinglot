package com.avangarde.parkinglot.auxs.intermeds;

import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.repositories.*;
import com.avangarde.parkinglot.auxs.fileIO.read.ReadFromFileImpl;
import com.avangarde.parkinglot.auxs.fileIO.Utils;

public class PersistLotAndVehiclesFromFile {

    private static final String INPUT_PATH = Utils.resourcesPath() + "input.txt";
    private final JPARepo parkingLotRepository;
    private final JPARepo parkingFloorRepository;
    private final JPARepo parkingSpotRepository;
    private final JPARepo vehicleRepository;

    public PersistLotAndVehiclesFromFile() {
        this.parkingLotRepository = new ParkingLotJPARepo();
        this.parkingFloorRepository = new ParkingFloorJPARepo();
        this.parkingSpotRepository = new ParkingSpotJPARepo();
        this.vehicleRepository = new VehicleJPARepo();
    }

    public static void main(String[] args) { // PersistTest
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
            vehicleRepository.create(vehicle);
            vehicle.setId(vehicle.getId());
        }

        System.out.println("Added " + vehicles.size() + " vehicles into DB.");
    }

    private void storeParking(ReadFromFileImpl inputFile) {
        System.out.println("Storing parking spots into DB...");
        int numberOfSpots = 0;
        // read from file
        ParkingLot lot = inputFile.readParkingLot(INPUT_PATH); // also creates dependencies (floors, spots)
        // store

        for (var floor : lot.getFloors()) {
            floor.setLot(lot);

            for (var spot : floor.getSpots()) {
                spot.setFloor(floor);
                numberOfSpots++;
            }
        }

        parkingLotRepository.create(lot);
        System.out.println("Added " + numberOfSpots + " parking spots to DB.");
    }
}
