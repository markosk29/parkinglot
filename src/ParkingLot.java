import Vehicles.Vehicle;

import java.util.ArrayList;

public class ParkingLot {
    private ArrayList<ParkingFloor> floors;

    public ParkingLot(int noOffloors) {
        this.floors = new ArrayList<>();
        this.createFloors(noOffloors);
    }

    private void createFloors(int noOfFloors) {
        int minFloors = 3;
        for(int i = 0; i < minFloors; i++) {
            ParkingFloor floor = new ParkingFloor();
            floor.setIdParkingFloor(i);

            this.addFloor(floor);
        }
    }

    public boolean addFloor(ParkingFloor floor) {
        if(floors.size() < 10) {
            return floors.add(floor);
        }

        return false;
    }

    public boolean parkVehicle(Vehicle vehicle) {

        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        return false;
    }

    public String summary() {
        return "";
    }

}
