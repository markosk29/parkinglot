package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingFloor;

public interface ParkingFloorRepository {
    /**
     *
     * @return id of the generated row
     */
    int createOne(ParkingFloor parkingFloor, int associatedParkingLotId);
}
