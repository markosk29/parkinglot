package Spot;

import Vehicles.Vehicle;
import Parking.ParkingSpot;

public class Motorbike extends ParkingSpot {

    public Motorbike(Vehicle vehicle) {
        super(false, vehicle, SpotType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for motorbikes.";
    }
}
