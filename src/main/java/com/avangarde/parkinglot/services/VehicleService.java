package com.avangarde.parkinglot.services;

import com.avangarde.parkinglot.entities.Vehicle;
import com.avangarde.parkinglot.repositories.ParkingSpotJPARepo;
import com.avangarde.parkinglot.repositories.VehicleJPARepo;

public class VehicleService {
    private final VehicleJPARepo vehicleRepository;
    private final ParkingSpotJPARepo parkingSpotRepository;

    public VehicleService() {
        this.vehicleRepository = new VehicleJPARepo();
        this.parkingSpotRepository = new ParkingSpotJPARepo();
    }

    public void park(Vehicle vehicle) {
        this.parkingSpotRepository.OccupySpotByVehicle(vehicle);
    }

    public void unpark(Vehicle vehicle) {
        this.parkingSpotRepository.leaveSpot(vehicle);
//        this.vehicleRepository.delete(vehicle);
    }
}
