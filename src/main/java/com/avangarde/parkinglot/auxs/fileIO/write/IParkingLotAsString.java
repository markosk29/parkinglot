package com.avangarde.parkinglot.auxs.fileIO.write;


import com.avangarde.parkinglot.entities.ParkingFloor;
import com.avangarde.parkinglot.entities.ParkingLot;

public interface IParkingLotAsString {

    String write(ParkingLot parkingLot);

    String write(ParkingFloor parkingFloor);
}
