package com.avangarde.parkinglot.database.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingFloor;

import java.sql.SQLException;

public interface ParkingFloorRepository {
    /**
     * @return id of the generated row
     */
    int createOne(ParkingFloor parkingFloor, int associatedParkingLotId);

    ParkingFloor findByIdParkingFloor(int id) throws SQLException;

}
