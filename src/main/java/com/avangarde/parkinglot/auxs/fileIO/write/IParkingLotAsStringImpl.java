package com.avangarde.parkinglot.auxs.fileIO.write;

import com.avangarde.parkinglot.entities.ParkingFloor;
import com.avangarde.parkinglot.entities.ParkingLot;

public class IParkingLotAsStringImpl implements IParkingLotAsString {
    @Override
    public String write(ParkingLot parkingLot) {
        StringBuilder parkingLotAsString = new StringBuilder();
        parkingLotAsString.append("PSTART \n");
        parkingLotAsString.append(parkingLot.getFloors().size() + " \n");
        int floorCount;
        for (floorCount = 0; floorCount < parkingLot.getFloors().size() - 1; floorCount++
        ) {

            parkingLotAsString.append(write(parkingLot.getFloors().get(floorCount)));
            parkingLotAsString.append("\n");
        }

        parkingLotAsString.append(write(parkingLot.getFloors().get(floorCount)));
        parkingLotAsString.append("\n");

        parkingLotAsString.append("PEND");

        return parkingLotAsString.toString();


    }

    @Override
    public String write(ParkingFloor parkingFloor) {
        StringBuilder parkingFloorAsString = new StringBuilder();
        parkingFloorAsString.append(parkingFloor.getLevel() + " ");

        for (var spot : parkingFloor.getSpots()
        ) {
            parkingFloorAsString.append(spot + " " + 5 % parkingFloor.getSpots().size() + " ");
        }

        return parkingFloorAsString.toString();
    }

}
