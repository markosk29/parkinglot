package com.avangarde.parkinglot.parking;

import com.avangarde.parkinglot.parking.ParkingSpotLotSize;
import com.avangarde.parkinglot.parking.entities.ParkingFloor;

import java.util.List;

public interface FloorBuilder {

    ParkingFloor createParkingFloor(int floorNo, List<ParkingSpotLotSize> parkingSpotLotSizes);
}
