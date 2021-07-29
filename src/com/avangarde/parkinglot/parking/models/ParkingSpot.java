package com.avangarde.parkinglot.parking.models;

import com.avangarde.parkinglot.vehicle.models.Vehicle;

public abstract class ParkingSpot {

    private boolean isOcuppied;
    private Vehicle vehicle;
    private SpotType type;

    public ParkingSpot(boolean isOcuppied, SpotType type) {
        this.isOcuppied = isOcuppied;
        this.type = type;
    }

    public boolean spotAvailable() {
        return !(this.isOcuppied);
    }


    public void occupySpot() {
        this.isOcuppied = true;
    }

    public void leaveSpot() {
        this.isOcuppied = false;
    }

    public abstract String getInfo();

    public boolean isOcuppied() {
        return this.isOcuppied;
    }

    public void setOcuppied(boolean ocuppied) {
        isOcuppied = ocuppied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public SpotType getType() {
        return type;
    }

    public void setType(SpotType type) {
        this.type = type;
    }
}
