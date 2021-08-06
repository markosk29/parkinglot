package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.models.ParkingSpot;

import java.sql.SQLException;

public interface ParkingSpotRepository {
    ParkingSpot findByIdParkingSpot(int id) throws SQLException;
}
