package com.avangarde.parkinglot.old.database.repositories;

import com.avangarde.parkinglot.old.parking.models.ParkingSpot;

import java.sql.SQLException;

public interface ParkingSpotRepository {
    /**
     * @return id of the generated row
     */
    int createOne(ParkingSpot parkingSpot, int associatedParkingFloorId);
    ParkingSpot findByIdParkingSpot(int id) throws SQLException;
}
