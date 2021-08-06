package com.avangarde.parkinglot.IO.write;

import com.avangarde.parkinglot.parking.entities.ParkingFloor;
import com.avangarde.parkinglot.parking.entities.ParkingLot;

public interface IParkingLotAsString {

    String write(ParkingLot parkingLot);
    String write(ParkingFloor parkingFloor);
}
