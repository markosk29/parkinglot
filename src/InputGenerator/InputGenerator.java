package InputGenerator;

import com.avangarde.parkinglot.parking.models.*;
import com.avangarde.parkinglot.parking.services.ParkingLot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class InputGenerator {
    private static final int MIN_FLOORS = 2;
    private static final int MAX_FLOORS = 10;
    private static Random random = new Random();

    /**
     * Returns a randomly configured parking lot with all spots free
     * @return parking lot
     */
    public static ParkingLot createParkingLot() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var noOfFloors = rand(MIN_FLOORS, MAX_FLOORS); // inclusive
        ParkingLot lot = new ParkingLot(noOfFloors);
        for (int floorCount = 0; floorCount < noOfFloors; floorCount++) { // create each floor
            for (var type : SpotType.values()) { // create spot types and associated floors
                ArrayList<ParkingSpot> spots = getRndSizedParkingSpots(type);
                if (spots == null) continue; // skip creating spots of that particular type
                // add spots to parking floor
                lot.getFloors().get(floorCount).addParkingSpots(type, spots);
            }
        }
        return lot;
    }

    // HELPER METHODS

    private static ArrayList<ParkingSpot> getRndSizedParkingSpots(SpotType type) {
        if ( rand(0, 1) == 0) {
            return null;
        }
        var maxSpots = rand(0, 100);
        var spots = new ArrayList<ParkingSpot>();
        for (int spotCount = 0; spotCount < maxSpots; spotCount++) {
            // create spots
            spots.add(createParkingSpot(type));
        }
        return spots;
    }

    private static ParkingSpot createParkingSpot(SpotType type) {
        switch (type) {
            case CAR:
                return new CarSpot();
            case BIKE:
                return new BikeSpot();
            case ELECTRIC:
                return new ElectricSpot();
            case HANDICAP:
                return new HandicapSpot();
            case MOTORBIKE:
                return new MotorbikeSpot();
            default:
//                TODO: Throw exception
                return null;
        }
    }



    private static int rand (int min, int max) {
       return (int) (Math.floor((Math.random() * (max-min+1) + min)));
    }

    public static void main (String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        InputGenerator.createParkingLot().summary();
    }
}
