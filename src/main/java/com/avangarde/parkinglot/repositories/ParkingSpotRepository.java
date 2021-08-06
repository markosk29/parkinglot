package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingSpot;

public interface ParkingSpotRepository {
    /**
     * @return id of the generated row
     */
    int createOne(ParkingSpot parkingSpot, int associatedParkingFloorId);
}
