package com.avangarde.parkinglot.parking.entities;

import com.avangarde.parkinglot.database.Identity;
import com.avangarde.parkinglot.parking.entities.ParkingFloor;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot extends Identity {
    List<ParkingFloor> floors = new ArrayList<>();

    public ParkingLot() {
    }

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public ParkingLot(int noOfFloors) {
        createFloors(noOfFloors);
    }

    private void createFloors(int noOfFloors) {
        int minFloors = 3;
        for(int i = 0; i < minFloors; i++) {
            ParkingFloor floor = new ParkingFloor(i);
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
        for (var pf : floors
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

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void summary() {
        for (ParkingFloor floor:
                floors) {
            System.out.print("Floor " + floor.getParkingFloorNumber() + ":\n" + floor.getTotalSpotsInParkingLot());
            System.out.print("Free spots: " + floor.getFreeSpotsSummary() + "\n");
            System.out.println();
        }
    }
}
