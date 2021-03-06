package com.avangarde.parkinglot.auxs.fileIO.randomgeneration;

import com.avangarde.parkinglot.entities.ParkingFloor;
import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.auxs.intermeds.FloorBuilder;
import com.avangarde.parkinglot.auxs.intermeds.FloorBuilderImpl;
import com.avangarde.parkinglot.auxs.intermeds.ParkingSpotLotSize;
import com.avangarde.parkinglot.auxs.intermeds.SpotType;

import java.util.ArrayList;
import java.util.List;

public class ParkingGenerator {
    private static final int MIN_FLOORS = 2;
    private static final int MAX_FLOORS = 5;

    public static final int MIN_NUMBER_OF_SPOTS = 0;
    public static final int MAX_NUMBER_OF_SPOTS = 50;

    private final FloorBuilder floorBuilder = new FloorBuilderImpl();

    /**
     * TODO: Extract method in a separate interface
     * Returns a randomly configured parking lot with all spots free
     *
     * @return parking lot
     */
    public ParkingLot createParkingLot() {
        var noOfFloors = rand(MIN_FLOORS, MAX_FLOORS); // inclusive
        var parkingLot = new ParkingLot();
        List<ParkingFloor> floors = new ArrayList<>();
        for (int floorCount = 0; floorCount < noOfFloors; floorCount++) {
            floors.add(floorBuilder.createParkingFloor(floorCount, generateRandomSpotLots()));
        }
        return parkingLot;
    }


    private List<ParkingSpotLotSize> generateRandomSpotLots() {
        List<ParkingSpotLotSize> spotsLots = new ArrayList<>();
        for (var type : SpotType.values()) { // create spot types
            if (!acceptOrDecline()) continue; // skip creating spots of that particular type
            else {
                var randomMaxSpots = rand(MIN_NUMBER_OF_SPOTS, MAX_NUMBER_OF_SPOTS);
                var lot = ParkingSpotLotSize.ParkingSpotLotSizeBuilder.builder().type(type).size(randomMaxSpots).build();
                spotsLots.add(lot);
            }
        }
        return spotsLots;
    }

    private static int rand(int min, int max) {
        return (int) (Math.floor((Math.random() * (max - min + 1) + min)));
    }


    // HELPER METHODS

    /**
     * Randomly chooses between 0 and 1; We use it to decide if we create a particular spot type in a floor
     *
     * @return boolean
     */
    private static boolean acceptOrDecline() {
        return rand(0, 1) != 0;
    }

}
