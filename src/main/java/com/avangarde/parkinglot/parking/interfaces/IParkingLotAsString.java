package com.avangarde.parkinglot.parking.interfaces;

import com.avangarde.parkinglot.parking.entities.ParkingFloor;
import com.avangarde.parkinglot.parking.entities.ParkingLot;

public interface IParkingLotAsString {

    String write(ParkingLot parkingLot);
    String write(ParkingFloor parkingFloor);
}
