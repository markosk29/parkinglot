package com.avangarde.parkinglot.auxs.fileIO.read;

import com.avangarde.parkinglot.entities.ParkingFloor;
import com.avangarde.parkinglot.entities.ParkingSpot;
import com.avangarde.parkinglot.auxs.intermeds.ParkingSpotLotSize;
import com.avangarde.parkinglot.auxs.intermeds.SpotType;
import com.avangarde.parkinglot.auxs.intermeds.FloorBuilder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

//////////////////////////
//NOT FINAL
//////////////////////////
//importer
public class Importer {
    private BufferedInputStream input;
    private FileInputStream fileInputStream;

    private StringBuilder stringBuilder;

    private FloorBuilder floorBuilder;

    //createPFloor(int floorNo, List<>)
    //

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

    public ArrayList<ParkingFloor> getFloorObjectsFromString(ArrayList<String> floorStrings) {
        ArrayList<ParkingFloor> parkingFloors = new ArrayList<>();
        Map<SpotType, List<ParkingSpot>> totalSpots;

        for(String floorString : floorStrings) {
            String[] floorElements = floorString.split(" ");
            int floorId = Integer.parseInt(floorElements[0].trim());

            ArrayList<ParkingSpotLotSize> parkingSpotLotSizes = new ArrayList<>();

            for(int i = 1; i < floorElements.length - 1; i += 2) {
                String typeElement = floorElements[i].toUpperCase();
                String sizeElement = floorElements[i + 1];

                ParkingSpotLotSize parkingSpotLotSize = createParkingSpotLotSize(typeElement, sizeElement);

                if(parkingSpotLotSize != null) {
                    parkingSpotLotSizes.add(parkingSpotLotSize);
                }
            }

            ParkingFloor newFloor = floorBuilder.createParkingFloor(floorId, parkingSpotLotSizes);

            totalSpots = this.createSpots(floorElements);

            totalSpots.clear();

            parkingFloors.add(newFloor);
        }

        return parkingFloors;
    }

    private ParkingSpotLotSize createParkingSpotLotSize(String typeElement, String sizeElement) {
        try {
            return ParkingSpotLotSize.ParkingSpotLotSizeBuilder.builder()
                    .type(SpotType.valueOf(typeElement))
                    .size(Integer.parseInt(sizeElement))
                    .build();

        } catch(NumberFormatException e) {
            System.out.println("Invalid input element. Expected number, received " + sizeElement);
        } catch(IllegalArgumentException e) {
            System.out.println("Invalid input element. Expected SpotType, received " + typeElement);
        } catch(Exception e) {
            System.out.println("Could not create parking spot lot. Received input " + sizeElement + " , " + typeElement);
        }

        return null;
    }


    private Map<SpotType, List<ParkingSpot>> createSpots(String[] floorElements) {
        Map<SpotType, List<ParkingSpot>> totalSpots = new HashMap<>();
        List<ParkingSpot> parkingSpots = new ArrayList<>();

        SpotType foundType = SpotType.CAR;
        boolean typeExists = false;

        for(int i = 1; i < floorElements.length; i++) {
            String carType = floorElements[i - 1].trim().toUpperCase();

            if(!floorElements[i].trim().isEmpty()) {
                parkingSpots.add(new ParkingSpot(SpotType.valueOf(carType)));
            }

            if(parkingSpots.size() == 0 && typeExists) {
                totalSpots.put(foundType, new ArrayList<>());

            } else if(typeExists ) {
                totalSpots.put(foundType, parkingSpots);
            }

            parkingSpots.clear();
            typeExists = false;
        }

        return totalSpots;
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
