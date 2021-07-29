package Spot;

import Vehicles.Vehicle;
import Parking.ParkingSpot;

public class ElectricSpot extends ParkingSpot {

    public ElectricSpot() {
        super(false, SpotType.ELECTRIC);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for motorbikes.";
    }
}
