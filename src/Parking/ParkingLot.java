package Parking;

import Parking.ParkingFloor;
import Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    List<ParkingFloor> floors = new ArrayList<>();

    private void createFloors(int noOfFloors) {
        int minFloors = 3;
        for(int i = 0; i < minFloors; i++) {
            ParkingFloor floor = new ParkingFloor(i);
            floor.setIdParkingFloor(i);

            this.addFloor(floor);
        }
    }

    public boolean addFloor(ParkingFloor floor) {
        if(floors.size() < 10) {
            return floors.add(floor);
        }
        System.out.println("Too many floors!!");
        return false;
    }


    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingFloor pf : floors
        ) {
            if (pf.occupySpotOnFloor(vehicle)) {
                System.out.println(String.valueOf(vehicle.getType()) + " parked!");
                return true;
            }
        }
        return false;

    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingFloor pf : floors
        ) {
            if (pf.leaveSpotOnFloor(vehicle)) {
                System.out.println("New free spot");
                return true;
            }
        }
        return false;

    }


    public void summary() {
        for (ParkingFloor floor:
                floors) {
            System.out.println("Floor " + floor.getIdParkingFloor() + " free spots: " + floor.getFreeSpotsSummary() + "\n");
        }
    }
}
