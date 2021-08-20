package com.avangarde.parkinglot.auxs.intermeds;

import com.avangarde.parkinglot.auxs.fileIO.Utils;
import com.avangarde.parkinglot.auxs.fileIO.read.ReadFromFileImpl;
import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.repositories.*;
import org.springframework.stereotype.Component;

@Component
public class PersistLotAndVehiclesFromFile {

    private static final String INPUT_PATH = Utils.resourcesPath() + "input.txt";
    private final JPARepo parkingLotRepository;
    private final JPARepo parkingFloorRepository;
    private final JPARepo parkingSpotRepository;
    private final JPARepo vehicleRepository;

    public PersistLotAndVehiclesFromFile(ParkingLotJPARepo parkingLotRepository,
                                         ParkingFloorJPARepo parkingFloorRepository,
                                         ParkingSpotJPARepo parkingSpotRepository,
                                         VehicleJPARepo vehicleRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingFloorRepository = parkingFloorRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.vehicleRepository = vehicleRepository;
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
