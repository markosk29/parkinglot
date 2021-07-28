package Vehicles;

public abstract class Vehicle {
    private VehicleType type;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public abstract String getInfo();
}
