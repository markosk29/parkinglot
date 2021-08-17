package com.avangarde.parkinglot.current.services;

import com.avangarde.parkinglot.current.entities.Vehicle;
import com.avangarde.parkinglot.current.repositories.ParkingSpotRepository;
import com.avangarde.parkinglot.current.repositories.VehicleRepository;

public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    public VehicleService() {
        this.vehicleRepository = new VehicleRepository();
        this.parkingSpotRepository = new ParkingSpotRepository();
    }

    public void park(Vehicle vehicle) {
        this.parkingSpotRepository.OccupySpotByVehicle(vehicle);
    }

    public void unpark(Vehicle vehicle) {
        this.parkingSpotRepository.leaveSpot(vehicle);
        this.vehicleRepository.delete(vehicle);
    }
}
