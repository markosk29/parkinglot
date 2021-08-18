package com.avangarde.parkinglot.current.services;

import com.avangarde.parkinglot.current.entities.Vehicle;
import com.avangarde.parkinglot.current.repositories.VehicleJPARepo;

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
        this.vehicleRepository.delete(vehicle);
    }
}
