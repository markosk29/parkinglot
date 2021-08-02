package com.avangarde;

import com.avangarde.parkinglot.parking.models.ParkingSpot;
import com.avangarde.parkinglot.parking.models.SpotType;
import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.utils.InputFileImpl;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String path = "input.txt";

        InputFileImpl impl = new InputFileImpl();

        ParkingLot parkingLot =  impl.readParkingLot(path);

        parkingLot.summary();
    }
}
