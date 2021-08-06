package com.avangarde.parkinglot.parking.interfaces;

import com.avangarde.parkinglot.parking.ParkingSpotLotSize;
import com.avangarde.parkinglot.parking.entities.ParkingFloor;

import java.util.List;

public interface IFloorBuilder {

    ParkingFloor createParkingFloor(int floorNo, List<ParkingSpotLotSize> parkingSpotLotSizes);
}
