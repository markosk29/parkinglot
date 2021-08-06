package com.avangarde.parkinglot.repositories;

import com.avangarde.parkinglot.parking.entities.ParkingLot;

public interface ParkingLotRepository {
    /**
     *
     * @param parkingLot
     * @return id of the generated row
     */
    int createOne(ParkingLot parkingLot);
}
