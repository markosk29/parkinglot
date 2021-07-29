package com.avangarde.parkinglot.parking.services;

import com.avangarde.parkinglot.parking.models.SpotType;
import com.avangarde.parkinglot.parking.models.ParkingSpot;
import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private int idParkingFloor;
    private Map<SpotType, List<ParkingSpot>> totalSpots;

    public ParkingFloor(int idParkingFloor, Map<SpotType, List<ParkingSpot>> totalSpots) {
        this.idParkingFloor = idParkingFloor;
        this.totalSpots = totalSpots;
    }

    public ParkingFloor(int idParkingFloor) {
        this.idParkingFloor = idParkingFloor;
    }

    public void setIdParkingFloor(int idParkingFloor) {
        this.idParkingFloor = idParkingFloor;
    }

    public int getIdParkingFloor() {
        return idParkingFloor;
    }

    public void addParkingSpots(SpotType type, ParkingSpot[] spots) {

        if (!this.totalSpots.containsKey(type)) {
            ArrayList<ParkingSpot> parkingSpots = (ArrayList<ParkingSpot>) Arrays.asList(spots);
            totalSpots.put(type, parkingSpots);
            System.out.println("Added " +spots.length+ " com.avangarde.parkinglot.parking spots, type: " + type);
        } else {
            totalSpots.get(type).addAll(Arrays.asList(spots));
            System.out.println("Spots of type " + type + " exists, added new com.avangarde.parkinglot.parking spots. New total: " + totalSpots.size());
        }
    }



    public boolean occupySpotOnFloor(Vehicle vehicle) {
        String spotType = String.valueOf(vehicle.getType());
        if (this.totalSpots.containsKey(SpotType.valueOf(spotType))) {
            for (ParkingSpot ps: totalSpots.get(SpotType.valueOf(spotType))
                 ) {
                if (ps.spotAvailable()) {
                    System.out.println("Occupying spot.....");
                    ps.occupySpot();
                    return true;
                }
            }
        }
        System.out.println("No spots available on floor: " + this.idParkingFloor);
        return false;
    }

    public boolean leaveSpotOnFloor(Vehicle vehicle) {
        String spotType = String.valueOf(vehicle.getType());
        if (this.totalSpots.containsKey(SpotType.valueOf(spotType))) {
            for (ParkingSpot ps: totalSpots.get(SpotType.valueOf(spotType))
            ) {
                if (!ps.spotAvailable()) {
                    System.out.println("Freeing spot.....");
                    ps.leaveSpot();
                    return true;
                }
            }
        }
        System.out.println("New free spot on floor: " + this.idParkingFloor);
        return false;
    }




    public String getFreeSpotsSummary() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (var entry : totalSpots.entrySet()) {
            for (var spot: entry.getValue()) {
                if (!spot.isOcuppied()) {
                    count++;
                }
            }
            //            Lambda Expressions variant:
//            int count = (int) entry.getValue().stream().filter(spot -> !spot.isOcuppied()).count();
            stringBuilder.append(entry.getKey() + ":" + count + " free spots, ");
        }
        return stringBuilder.toString();
    }
}
