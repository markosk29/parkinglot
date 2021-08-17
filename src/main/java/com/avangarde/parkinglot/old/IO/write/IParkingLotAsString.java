package com.avangarde.parkinglot.old.IO.write;

import com.avangarde.parkinglot.old.parking.models.ParkingFloor;
import com.avangarde.parkinglot.old.parking.models.ParkingLot;

public interface IParkingLotAsString {

    String write(ParkingLot parkingLot);
    String write(ParkingFloor parkingFloor);
}
