package Vehicles;

public class Car extends Vehicle {

    public Car() {
        super(VehicleType.CAR);
    }

    @Override
    public String getInfo() {
        return "This is a car.";
    }
}
