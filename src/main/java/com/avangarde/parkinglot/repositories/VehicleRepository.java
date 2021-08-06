package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.vehicle.entities.Vehicle;


public interface VehicleRepository {
    /**
     *
     * @param vehicle
     * @return generated id of newly inserted row
     */
    int createOne(Vehicle vehicle);
}
