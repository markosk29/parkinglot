package com.avangarde.parkinglot.parking.interfaces;

import com.avangarde.parkinglot.parking.models.ParkingSpotLotSize;
import com.avangarde.parkinglot.parking.services.ParkingFloor;

import java.util.List;

public interface IFloorBuilder {

    ParkingFloor createParkingFloor(int floorNo, List<ParkingSpotLotSize> parkingSpotLotSizes);
}
