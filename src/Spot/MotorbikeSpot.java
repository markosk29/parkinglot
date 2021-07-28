package Spot;

import Vehicles.Vehicle;

public class MotorbikeSpot extends ParkingSpot {

    public MotorbikeSpot(Vehicle vehicle) {
        super(false, vehicle, SpotType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for motorbikes.";
    }
}
