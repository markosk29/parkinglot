package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.services.ParkingLot;

import java.sql.SQLException;
import java.util.List;

public interface ParkingRepository {
    List<ParkingLot> getParkingLots () throws SQLException;
}
