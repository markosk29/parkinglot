package com.avangarde.parkinglot.IO.write;

import com.avangarde.parkinglot.parking.SpotType;
import com.avangarde.parkinglot.parking.entities.ParkingFloor;
import com.avangarde.parkinglot.parking.entities.ParkingLot;

public class IParkingLotAsStringImpl implements IParkingLotAsString {
    //        PSTART
    //        3

    //        PEND
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

    //            1 CAR 150 MOTORBIKE 20 BIKE 100
//            2 CAR 120    MOTORBIKE 10 BIKE 0
//            3 CAR 180 MOTORBIKE 20
    @Override
    public String write(ParkingFloor parkingFloor) {
        StringBuilder parkingFloorAsString = new StringBuilder();
        parkingFloorAsString.append(parkingFloor.getParkingFloorNumber() + " ");

        for (SpotType spotType : parkingFloor.getSpotPairs().keySet()
        ) {
            parkingFloorAsString.append(spotType + " " + parkingFloor.getSpotPairs().get(spotType).size() + " ");
        }

        return parkingFloorAsString.toString();
    }

}
