package Spot;

import Vehicles.Vehicle;

public class HandicapSpot extends ParkingSpot {

    public HandicapSpot(Vehicle vehicle) {
        super(false, vehicle, SpotType.HANDICAP);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for handicapped.";
    }
}
