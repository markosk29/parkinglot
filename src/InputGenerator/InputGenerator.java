package InputGenerator;

import com.avangarde.parkinglot.parking.interfaces.IFloorBuilder;
import com.avangarde.parkinglot.parking.models.*;
import com.avangarde.parkinglot.parking.services.ParkingFloor;
import com.avangarde.parkinglot.parking.services.ParkingLot;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class InputGenerator implements IFloorBuilder {
    private static final int MIN_FLOORS = 2;
    private static final int MAX_FLOORS = 10;
    private static Random random = new Random();

    /**
     * Returns a randomly configured parking lot with all spots free
     * @return parking lot
     */
    public ParkingLot createParkingLot() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var noOfFloors = rand(MIN_FLOORS, MAX_FLOORS); // inclusive
        var parkingLot = new ParkingLot();
        for (int floorCount = 0; floorCount < noOfFloors; floorCount++) {
            parkingLot.addFloor(createParkingFloor(floorCount, generateRandomSpotLots()));
        }
        return parkingLot;
    }

    // HELPER METHODS

    private static boolean acceptOrDecline() {
        if ( rand(0, 1) == 0) {
            return false;
        }
        return true;

    }
    private List<ParkingSpotLotSize> generateRandomSpotLots() {
        List<ParkingSpotLotSize> spotsLots = new ArrayList<>();
        for (var type : SpotType.values()) { // create spot types
            if (!acceptOrDecline()) continue; // skip creating spots of that particular type
            else {
                var randomMaxSpots = rand(0, 150);
                var lot = ParkingSpotLotSize.ParkingSpotLotSizeBuilder.builder().type(type).size(randomMaxSpots).build();
                spotsLots.add(lot);
            }
        }
        return spotsLots;
    }

    private static int rand (int min, int max) {
       return (int) (Math.floor((Math.random() * (max-min+1) + min)));
    }

    public static void main (String[] args) {
//        InputGenerator.createParkingLot().summary();
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
            // Breaks if given duplicate types of ParkingSpotLotSize type one after another
            spotPairs.put(lot.getType(), parkingSpots);
        }
        var floor = ParkingFloor.ParkingFloorBuilder.builder().floorID(floorNo).spotPairs(spotPairs).build();
        return floor;
    }
}
