package com.avangarde.parkinglot.parking.services;

import com.avangarde.parkinglot.parking.models.ParkingSpot;
import com.avangarde.parkinglot.parking.models.SpotType;
import com.avangarde.parkinglot.vehicle.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private int floorID;
    private Map<SpotType, List<ParkingSpot>> spotPairs;

    private ParkingFloor(int idParkingFloor, Map<SpotType, List<ParkingSpot>> totalSpots) {
        this.floorID = idParkingFloor;
        this.spotPairs = totalSpots;
    }

    public static class ParkingFloorBuilder {
        private int floorID;
        private Map<SpotType, List<ParkingSpot>> spotPairs;

        public static ParkingFloorBuilder builder() {
            return new ParkingFloorBuilder();
        }

        public ParkingFloorBuilder floorID (int floorID) {
            this.floorID = floorID;
            return this;
        }

        public ParkingFloorBuilder spotPairs (Map<SpotType, List<ParkingSpot>> spotPairs) {
            this.spotPairs = spotPairs;
            return this;
        }

        public ParkingFloor build() {
            return new ParkingFloor(floorID, spotPairs);
        }
    }

    public ParkingFloor(int floorID) {
        this.floorID = floorID;
    }

    public void setFloorID(int floorID) {
        this.floorID = floorID;
    }

    public int getFloorID() {
        return floorID;
    }

    public void addParkingSpots(SpotType type, List<ParkingSpot> spots) {
        if (!this.spotPairs.containsKey(type)) {
            spotPairs.put(type, spots);
            System.out.println("Added " + spots.size() + " parking spots, type: " + type);
        } else {
            spotPairs.get(type).addAll(spots);
            System.out.println("Spots of type " + type + " exists, added new parking spots. New total: " + spotPairs.size());
        }
    }

    public boolean occupySpotOnFloor(Vehicle vehicle) {
        String spotType = String.valueOf(vehicle.getType());
        if (this.spotPairs.containsKey(SpotType.valueOf(spotType))) {
            for (ParkingSpot ps : spotPairs.get(SpotType.valueOf(spotType))
            ) {
                if (ps.spotAvailable()) {
                    System.out.println("Occupying spot.....");
                    ps.occupySpot();
                    return true;
                }
            }
        }
        System.out.println("No spots available on floor: " + this.floorID);
        return false;
    }

    public boolean leaveSpotOnFloor(Vehicle vehicle) {
        String spotType = String.valueOf(vehicle.getType());
        if (this.spotPairs.containsKey(SpotType.valueOf(spotType))) {
            for (ParkingSpot ps : spotPairs.get(SpotType.valueOf(spotType))
            ) {
                if (!ps.spotAvailable()) {
                    System.out.println("Freeing spot.....");
                    ps.leaveSpot();
                    return true;
                }
            }
        }
        System.out.println("New free spot on floor: " + this.floorID);
        return false;
    }


    public String getFreeSpotsSummary() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (var entry : spotPairs.entrySet()) {
            for (var spot : entry.getValue()) {
                if (!spot.isOcuppied()) {
                    count++;
                }
            }
            //            Lambda Expressions variant:
//            int count = (int) entry.getValue().stream().filter(spot -> !spot.isOcuppied()).count();
            stringBuilder.append(entry.getKey() + ": " + count + "free spots");
        }
        return stringBuilder.toString();
    }

    public Map<SpotType, List<ParkingSpot>> getSpotPairs() {
        return spotPairs;
    }

    public void setSpotPairs(Map<SpotType, List<ParkingSpot>> spotPairs) {
        this.spotPairs = spotPairs;
    }
}
