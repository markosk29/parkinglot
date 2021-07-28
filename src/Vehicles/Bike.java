package Vehicles;

public class Bike extends Vehicle {
    public Bike() {
        super(VehicleType.BIKE);
    }

    @Override
    public String getInfo() {
        return "This is a bike.";
    }
}
