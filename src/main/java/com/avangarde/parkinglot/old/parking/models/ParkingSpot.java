package com.avangarde.parkinglot.old.parking.models;

import com.avangarde.parkinglot.old.database.Identity;
import com.avangarde.parkinglot.old.parking.SpotType;
import com.avangarde.parkinglot.old.vehicle.models.Vehicle;

public abstract class ParkingSpot extends Identity {
    private int parkingFloorId;
    private int vehicleId;
    private Vehicle vehicle;
    private boolean isOccupied;
    private SpotType type;

    public int getParkingFloorId() {
        return parkingFloorId;
    }

    public void setParkingFloorId(int parkingFloorId) {
        this.parkingFloorId = parkingFloorId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot(boolean isOcuppied, SpotType type) {
        this.isOccupied = isOcuppied;
        this.type = type;
    }

    public boolean spotAvailable() {
        return !(this.isOccupied);
    }


    public void occupySpot() {
        this.isOccupied = true;
    }

    public void leaveSpot() {
        this.isOccupied = false;
    }

    public abstract String getInfo();

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public SpotType getType() {
        return type;
    }

    public void setType(SpotType type) {
        this.type = type;
    }
}
