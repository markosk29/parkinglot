package com.avangarde.parkinglot.auxs.intermeds;


import com.avangarde.parkinglot.entities.ParkingFloor;
import com.avangarde.parkinglot.entities.ParkingSpot;

import java.util.*;

public class FloorBuilderImpl implements FloorBuilder {
    @Override
    public ParkingFloor createParkingFloor(int floorNo, List<ParkingSpotLotSize> parkingSpotLotSizes) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        for (var lot : parkingSpotLotSizes) {
            for (int spotCount = 0; spotCount < lot.getSize(); spotCount++) {
                // adding parking spots to the parking floor
                parkingSpots.add(new ParkingSpot(lot.getType()));
            }
        }
        var floor = new ParkingFloor();
        floor.setSpots((Set<ParkingSpot>) parkingSpots);
        return floor;
    }
}
