package Vehicles;

public class Motorbike extends Vehicle {
    public Motorbike() {
        super(VehicleType.MOTORBIKE);
    }

    @Override
    public String getInfo() {
        return "This is a motorbike";
    }
}
