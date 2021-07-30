package com.avangarde.parkinglot.parking.models;

public abstract class ParkingSpot {

    private boolean isOcuppied;
    //    removed field from UML Diagram
//    private Vehicle vehicle;
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

    public SpotType getType() {
        return type;
    }

    public void setType(SpotType type) {
        this.type = type;
    }
}
