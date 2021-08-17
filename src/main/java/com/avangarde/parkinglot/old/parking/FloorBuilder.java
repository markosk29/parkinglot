package com.avangarde.parkinglot.old.parking;

import com.avangarde.parkinglot.old.parking.models.ParkingFloor;

import java.util.List;

public interface FloorBuilder {

    ParkingFloor createParkingFloor(int floorNo, List<ParkingSpotLotSize> parkingSpotLotSizes);
}
