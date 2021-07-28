package com.avangarde.parkinglot.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

//////////////////////////
//NOT FINAL
//////////////////////////
public class Import {
    private BufferedInputStream input;
    private FileInputStream fileInputStream;

    private StringBuilder stringBuilder;

    public String getFromFile(String path) {
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

        this.processString(stringBuilder.toString());

        return stringBuilder.toString();
    }

    public void processString(String inputString) {
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
