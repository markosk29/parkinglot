package Spot;

import Vehicles.Vehicle;

public abstract class ParkingSpot {

    private boolean isOcuppied;
    private Vehicle vehicle;
    private SpotType type;

    public ParkingSpot(boolean isOcuppied, Vehicle vehicle, SpotType type) {
        this.isOcuppied = isOcuppied;
        this.vehicle = vehicle;
        this.type = type;
    }

    public abstract String getInfo();

}
