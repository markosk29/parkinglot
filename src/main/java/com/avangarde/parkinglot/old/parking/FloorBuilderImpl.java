package com.avangarde.parkinglot.old.parking;

import com.avangarde.parkinglot.old.parking.models.ParkingFloor;
import com.avangarde.parkinglot.old.parking.models.ParkingSpot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloorBuilderImpl implements FloorBuilder {
    @Override
    public ParkingFloor createParkingFloor(int floorNo, List<ParkingSpotLotSize> parkingSpotLotSizes) {
        ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
        Map<SpotType, List<ParkingSpot>> spotPairs = new HashMap<>();
        for (var lot : parkingSpotLotSizes) {
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            for (int spotCount = 0; spotCount < lot.getSize(); spotCount++) {
                // adding parking spots to the parking floor
                parkingSpots.add(parkingSpotFactory.createParkingSpot(lot.getType()));
            }
            if (!spotPairs.containsKey(lot.getType())) {
                spotPairs.put(lot.getType(), parkingSpots);
            } else {
                var newList = spotPairs.get(lot.getType());
                newList.addAll(parkingSpots);
                spotPairs.put(lot.getType(), newList);
            }
        }
        var floor = ParkingFloor.ParkingFloorBuilder.builder().floorID(floorNo).spotPairs(spotPairs).build();
        return floor;
    }
}
