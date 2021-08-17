package com.avangarde.parkinglot.old.database.repositories;

import com.avangarde.parkinglot.old.parking.models.ParkingLot;

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
