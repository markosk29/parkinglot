package com.avangarde.parkinglot.parking.interfaces;

import com.avangarde.parkinglot.parking.services.ParkingFloor;
import com.avangarde.parkinglot.parking.services.ParkingLot;

public interface IParkingLotAsString {

    void write(ParkingLot parkingLot);
    void write(ParkingFloor parkingFloor);
}
