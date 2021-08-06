package com.avangarde.parkinglot.database.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingLot;

import java.sql.SQLException;

public interface ParkingLotRepository {
    /**
     *
     * @param parkingLot
     * @return id of the generated row
     */
    int createOne(ParkingLot parkingLot);
    ParkingLot findByIdParkingLot(int id) throws SQLException;
    ParkingLot loadLatestParkingLot();
}
