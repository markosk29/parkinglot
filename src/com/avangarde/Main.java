package com.avangarde;

import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.utils.InputFileImpl;

public class Main {
    public static void main(String[] args) {

        String path = "input.txt";

        InputFileImpl impl = new InputFileImpl();

        ParkingLot parkingLot =  impl.readParkingLot(path);

        parkingLot.summary();
    }
}
