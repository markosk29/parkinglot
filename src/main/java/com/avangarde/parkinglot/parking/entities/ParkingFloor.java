package com.avangarde.parkinglot.parking.entities;

import com.avangarde.parkinglot.database.Identity;
import com.avangarde.parkinglot.parking.SpotType;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;

import java.util.List;
import java.util.Map;

public class ParkingFloor extends Identity {

    private int parkingFloorNumber;
    private int parkingLotId;
    private Map<SpotType, List<ParkingSpot>> spotPairs;

    private ParkingFloor(int parkingFloorNumber, Map<SpotType, List<ParkingSpot>> totalSpots) {
        this.parkingFloorNumber = parkingFloorNumber;
        this.spotPairs = totalSpots;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
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

    public ParkingFloor(int parkingFloorNumber) {
        this.parkingFloorNumber = parkingFloorNumber;
    }

    public void setParkingFloorNumber(int parkingFloorNumber) {
        this.parkingFloorNumber = parkingFloorNumber;
    }

    public int getParkingFloorNumber() {
        return parkingFloorNumber;
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

                    ps.occupySpot();
                    return true;
                }
            }
        }

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
        System.out.println("New free spot on floor: " + this.parkingFloorNumber);
        return false;
    }


    public String getFreeSpotsSummary() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (var entry : this.spotPairs.entrySet()) {
            count = 0;

            for (var spot : entry.getValue()) {
                if (!spot.isOccupied()) {
                    count++;
                }
            }
            //            Lambda Expressions variant:
//            int count = (int) entry.getValue().stream().filter(spot -> !spot.isOcuppied()).count();
            stringBuilder.append("\n" + entry.getKey() + ": " + count + " free spots ");
        }
        return stringBuilder.toString();
    }

    public Map<SpotType, List<ParkingSpot>> getSpotPairs() {
        return spotPairs;
    }

    public void setSpotPairs(Map<SpotType, List<ParkingSpot>> spotPairs) {
        this.spotPairs = spotPairs;
    }

    public String getTotalSpotsInParkingLot() {
        StringBuilder stringBuilder = new StringBuilder();
        for (SpotType spotType : this.spotPairs.keySet()
        ) {
            stringBuilder.append("Total spots of type " + spotType + ": " + this.spotPairs.get(spotType).size() + "\n");
        }
        return stringBuilder.toString();
    }
}


