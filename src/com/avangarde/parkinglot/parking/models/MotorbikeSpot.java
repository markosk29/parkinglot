package Spot;

import Vehicles.Vehicle;
import Parking.ParkingSpot;

public class MotorbikeSpot extends ParkingSpot {

    public MotorbikeSpot() {
        super(false, SpotType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for motorbikes.";
    }
}
