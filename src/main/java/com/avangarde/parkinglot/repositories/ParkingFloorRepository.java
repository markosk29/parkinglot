package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.services.ParkingFloor;

import java.sql.SQLException;

public interface ParkingFloorRepository {
    ParkingFloor findByIdParkingFloor(int id) throws SQLException;

}
