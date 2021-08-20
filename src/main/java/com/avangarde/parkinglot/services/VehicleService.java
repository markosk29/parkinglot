package com.avangarde.parkinglot.services;

import com.avangarde.parkinglot.entities.Vehicle;
import com.avangarde.parkinglot.repositories.ParkingSpotJPARepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final ParkingSpotJPARepo parkingSpotRepository;

    @Autowired
    public VehicleService(ParkingSpotJPARepo parkingSpotRepo) {
        this.parkingSpotRepository = parkingSpotRepo;
    }

    public void park(Vehicle vehicle) {
        this.parkingSpotRepository.OccupySpotByVehicle(vehicle);
    }

    public void unpark(Vehicle vehicle) {
        this.parkingSpotRepository.leaveSpot(vehicle);
    }
}
