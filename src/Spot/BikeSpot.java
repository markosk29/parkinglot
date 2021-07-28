package Spot;

import Vehicles.Vehicle;

public class BikeSpot extends ParkingSpot {

    public BikeSpot(Vehicle vehicle) {
        super(false, vehicle, SpotType.BIKE);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for bikes.";
    }
}
