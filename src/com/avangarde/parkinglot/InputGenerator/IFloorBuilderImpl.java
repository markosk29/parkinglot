package com.avangarde.parkinglot.InputGenerator;

import com.avangarde.parkinglot.parking.interfaces.IFloorBuilder;
import com.avangarde.parkinglot.parking.models.ParkingSpot;
import com.avangarde.parkinglot.parking.models.ParkingSpotFactory;
import com.avangarde.parkinglot.parking.models.ParkingSpotLotSize;
import com.avangarde.parkinglot.parking.models.SpotType;
import com.avangarde.parkinglot.parking.services.ParkingFloor;
import com.avangarde.parkinglot.parking.services.ParkingLot;

import java.util.*;

public class IFloorBuilderImpl implements IFloorBuilder {
    private static final int MIN_FLOORS = 2;
    private static final int MAX_FLOORS = 10;
    public static final int TWO_CHARACTERS = 2;
    public static final int THREE_CHARACTERS = 3;
    public static final int ASCII_FOR_LETTER_A= 65;
    public static final int ASCII_FOR_LETTER_Z= 90;
    public static final int ASCII_FOR_NUMBER_0= 48;
    public static final int ASCII_FOR_NUMBER_9= 57;
    public static final int MIN_NUMBER_OF_SPOTS = 0;
    public static final int MAX_NUMBER_OF_SPOTS = 150;



    /**
     * TODO: Extract method in a separate interface
     * Returns a randomly configured parking lot with all spots free
     * @return parking lot
     */
    public ParkingLot createParkingLot() {
        var noOfFloors = rand(MIN_FLOORS, MAX_FLOORS); // inclusive
        var parkingLot = new ParkingLot();
        for (int floorCount = 0; floorCount < noOfFloors; floorCount++) {
            parkingLot.addFloor(createParkingFloor(floorCount, generateRandomSpotLots()));
        }
        return parkingLot;
    }


    @Override
    public ParkingFloor createParkingFloor(int floorNo, List<ParkingSpotLotSize> parkingSpotLotSizes) {
        ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
        Map<SpotType, List<ParkingSpot>> spotPairs = new HashMap<>();
        for(var lot : parkingSpotLotSizes) {
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            for(int spotCount = 0; spotCount < lot.getSize(); spotCount++) {
                // adding parking spots to the parking floor
                parkingSpots.add(parkingSpotFactory.createParkingSpot(lot.getType()));
            }
            if (! spotPairs.containsKey(lot.getType())) {
                spotPairs.put(lot.getType(), parkingSpots);
            }
            else {
                var newList = spotPairs.get(lot.getType());
                newList.addAll(parkingSpots);
                spotPairs.put(lot.getType(), newList);
            }
        }
        var floor = ParkingFloor.ParkingFloorBuilder.builder().floorID(floorNo).spotPairs(spotPairs).build();
        return floor;
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

    private static int rand (int min, int max) {
       return (int) (Math.floor((Math.random() * (max-min+1) + min)));
    }


    public  String createRandomPlate() {
        String generatedPlateCounty= getRandomContentForPlate(TWO_CHARACTERS, ASCII_FOR_LETTER_A, ASCII_FOR_LETTER_Z);
        String generatedPlateNumber = getRandomContentForPlate(TWO_CHARACTERS, ASCII_FOR_NUMBER_0, ASCII_FOR_NUMBER_9);
        String generatedPlateName = getRandomContentForPlate(THREE_CHARACTERS, ASCII_FOR_LETTER_A, ASCII_FOR_LETTER_Z);

        return generatedPlateCounty + generatedPlateNumber + generatedPlateName;
    }

    private  String getRandomContentForPlate(int totalNumberOfCharacters, int startCharacter, int endCharacter) {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(totalNumberOfCharacters);
        for (int i = 0; i < totalNumberOfCharacters; i++) {
            int randomLimitedInt = startCharacter + (int)
                    (random.nextFloat() * (endCharacter - startCharacter + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }


    // HELPER METHODS

    /**
     * Randomly chooses between 0 and 1; We use it to decide if we create a particular spot type in a floor
     * @return boolean
     */
    private static boolean acceptOrDecline() {
        if ( rand(0, 1) == 0) {
            return false;
        }
        return true;
    }

}
