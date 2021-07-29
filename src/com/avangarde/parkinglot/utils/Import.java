package com.avangarde.parkinglot.utils;

import com.avangarde.parkinglot.parking.services.ParkingFloor;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

//////////////////////////
//NOT FINAL
//////////////////////////
public class Import {
    private BufferedInputStream input;
    private FileInputStream fileInputStream;

    private StringBuilder stringBuilder;

    public void getObjectsFromFile(String path) {
        stringBuilder = new StringBuilder();

        try {
            fileInputStream = new FileInputStream(path);
            input = new BufferedInputStream(fileInputStream);

            int i;
            while((i = input.read()) != -1) {
                stringBuilder.append((char)i);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        this.getObjectsFromString(stringBuilder.toString());

        //return stringBuilder.toString();
    }

    public void getObjectsFromString(String inputString) {
        String[] inputArray = inputString.split("\n");
        boolean isFloors = false;
        boolean isVehicles = false;
        int noOfFloors = 0;
        ArrayList<String> floorStrings = new ArrayList<>();
        ArrayList<String> vehicleStrings = new ArrayList<>();

        for(String element : inputArray) {
            if(element.trim().equals("PSTART")) {
                isFloors = true;
            } else if(element.trim().equals("PEND")) {
                isFloors = false;
            } else if(isNumeric(element) && isFloors) {
                noOfFloors = Integer.parseInt(element.trim());
            } else if(!isNumeric(element) && isFloors) {
                floorStrings.add(element);
            }

            if(element.trim().equals("VehicleStart")) {
                isVehicles = true;
            } else if(element.trim().equals("VehicleEnd")) {
                isVehicles = false;
            } else if(!isNumeric(element) && isVehicles) {
                vehicleStrings.add(element);
            }
        }

        this.getFloorObjectsFromString(floorStrings);
    }

    public void getFloorObjectsFromString(ArrayList<String> floorStrings) {
        for(String floorString : floorStrings) {
            String[] floorElements = floorString.split(" ");

            for(String element : floorElements) {
                if(!element.trim().isEmpty()) {
                    System.out.println(element);
                }
            }
        }

    }

    public boolean isNumeric(String string) {
        string = string.trim();

        try {
            Integer.parseInt(string);
        } catch(NumberFormatException e) {
            return false;
        }

        return true;
    }
}
