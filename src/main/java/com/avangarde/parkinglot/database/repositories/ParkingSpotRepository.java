package com.avangarde.parkinglot.database.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingSpot;

import java.sql.SQLException;

public interface ParkingSpotRepository {
    /**
     * @return id of the generated row
     */
    int createOne(ParkingSpot parkingSpot, int associatedParkingFloorId);
    ParkingSpot findByIdParkingSpot(int id) throws SQLException;
}
