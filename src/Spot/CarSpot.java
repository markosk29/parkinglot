package Spot;

import Vehicles.Vehicle;
import Parking.ParkingSpot;

public class CarSpot extends ParkingSpot {

    public CarSpot() {
        super(false, SpotType.CAR);
    }

    @Override
    public String getInfo() {
        return "This parking spot is for cars.";
    }
}
