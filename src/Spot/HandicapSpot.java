package Spot;

import Vehicles.Vehicle;
import Parking.ParkingSpot;

public class HandicapSpot extends ParkingSpot {

    public HandicapSpot() {
        super(false, SpotType.HANDICAP);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for handicapped.";
    }
}
