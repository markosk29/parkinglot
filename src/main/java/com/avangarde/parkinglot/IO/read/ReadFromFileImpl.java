package com.avangarde.parkinglot.IO.read;

import com.avangarde.parkinglot.IO.write.IParkingLotAsStringImpl;
import com.avangarde.parkinglot.parking.entities.ParkingSpot;
import com.avangarde.parkinglot.parking.ParkingSpotFactory;
import com.avangarde.parkinglot.parking.SpotType;
import com.avangarde.parkinglot.parking.entities.ParkingFloor;
import com.avangarde.parkinglot.parking.entities.ParkingLot;
import com.avangarde.parkinglot.vehicle.entities.Vehicle;
import com.avangarde.parkinglot.vehicle.VehicleBuilderImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFromFileImpl implements ReadFromFile {

    private String filePath;
    private int noOfFloors = 0;

    @Override
    public ParkingLot readParkingLot(String path) {
        this.filePath = path;

        ArrayList<String> floorStrings = extractParkingString();

        ArrayList<ParkingFloor> parkingFloors = generateParkingFloors(floorStrings);

        return new ParkingLot(parkingFloors);
    }

    @Override
    public List<Vehicle> readVehicles(String path) {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        this.filePath = path;

        ArrayList<String> vehicleStrings = this.extractVehicleString();

        for(String vehicle : vehicleStrings) {
            String[] veh = vehicle.split(" ");
            Vehicle newVehicle;

            if(veh[0].trim().equals("BIKE")) {
                newVehicle = VehicleBuilderImpl.builder().createVehicle(veh[0].trim(), "N/A");
            } else {
                newVehicle = VehicleBuilderImpl.builder().createVehicle(veh[0].trim(), veh[1].trim());
            }

            if(newVehicle != null) {
                vehicleList.add(newVehicle);
            }
        }

        return vehicleList;
    }

    @Override
    public File generate(ParkingLot parkingLot, String path, String vehicles) {
        try {
            File myObj = new File(path);
            IParkingLotAsStringImpl iParkingLotAsString = new IParkingLotAsStringImpl();

            String allInput = iParkingLotAsString.write(parkingLot) + "" + vehicles;

            Files.writeString(Path.of(myObj.getPath()), allInput);


            return myObj;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public String readFromFile(String path) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            BufferedInputStream input = new BufferedInputStream(fileInputStream);

            int i;
            while((i = input.read()) != -1) {
                stringBuilder.append((char)i);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private ArrayList<ParkingFloor> generateParkingFloors(ArrayList<String> floorStrings) {
        ArrayList<ParkingFloor> parkingFloors = new ArrayList<>();

        for(String floor : floorStrings) {
            Map<SpotType, List<ParkingSpot>> totalSpots = new HashMap<>();
            String[] tempCurrentFloor = floor.split(" ");

            ArrayList<String> currentFloor = new ArrayList<>();

            for(String element : tempCurrentFloor) {
                if(!element.trim().isEmpty()) {
                    currentFloor.add(element);
                }
            }

            int floorId = 0;

            if(isNumeric(currentFloor.get(0).trim())) {
                floorId = Integer.parseInt(currentFloor.get(0).trim());
            }

            for(int i = 1; i < currentFloor.size(); i+=2) {
                List<ParkingSpot> parkingSpots = new ArrayList<>();

                if(isNumeric(currentFloor.get(i + 1).trim())
                        && !currentFloor.get(i + 1).trim().isEmpty()) {

                    for(int j = 0; j < Integer.parseInt(currentFloor.get(i + 1).trim()); j++) {
                        parkingSpots.add(
                                new ParkingSpotFactory()
                                        .createParkingSpot(currentFloor.get(i).trim()));
                    }
                }

                String spotType = currentFloor.get(i).trim();

                if(parkingSpots.size() == 0) {
                    totalSpots.put(new ParkingSpotFactory()
                            .createParkingSpot(spotType).getType(), new ArrayList<>());

                } else {
                    totalSpots.put(parkingSpots.get(0).getType(), parkingSpots);
                }
            }

            parkingFloors.add(ParkingFloor
                    .ParkingFloorBuilder
                    .builder()
                    .floorID(floorId)
                    .spotPairs(totalSpots)
                    .build());
        }

        return parkingFloors;
    }

    private ArrayList<String> extractVehicleString() {
        String[] inputArray = readFromFile(this.filePath).split("\n");
        boolean isVehicles = false;
        ArrayList<String> vehicleStrings = new ArrayList<>();

        for(String element : inputArray) {
            if(element.trim().equals("VehicleStart")) {
                isVehicles = true;
            } else if(element.trim().equals("VehicleEnd")) {
                isVehicles = false;
            } else if(!isNumeric(element) && isVehicles) {
                vehicleStrings.add(element);
            }
        }

        return vehicleStrings;
    }

    private ArrayList<String> extractParkingString() {
        String[] inputArray = readFromFile(this.filePath).split("\n");
        boolean isFloors = false;
        ArrayList<String> floorStrings = new ArrayList<>();

        for(String element : inputArray) {
            if(element.trim().equals("PSTART")) {
                isFloors = true;
            } else if(element.trim().equals("PEND")) {
                isFloors = false;
            } else if(isNumeric(element) && isFloors) {
                this.noOfFloors = Integer.parseInt(element.trim());
            } else if(!isNumeric(element) && isFloors) {
                floorStrings.add(element);
            }
        }

        return floorStrings;
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
