package com.avangarde.parkinglot.auxs.intermeds;

import com.avangarde.parkinglot.entities.ParkingFloor;

import java.util.List;

public interface FloorBuilder {

    ParkingFloor createParkingFloor(int floorNo, List<ParkingSpotLotSize> parkingSpotLotSizes);
}
