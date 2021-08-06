package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.services.ParkingLot;

import java.sql.SQLException;

public interface ParkingLotRepository {
    ParkingLot findByIdParkingLot(int id) throws SQLException;
    ParkingLot loadLatestParkingLot();
}
