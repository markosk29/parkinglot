package com.avangarde.parkinglot.auxs.fileIO.read;

import com.avangarde.parkinglot.entities.ParkingFloor;
import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.entities.ParkingSpot;
import com.avangarde.parkinglot.entities.Vehicle;
import com.avangarde.parkinglot.auxs.fileIO.write.IParkingLotAsStringImpl;
import com.avangarde.parkinglot.auxs.intermeds.SpotType;
import com.avangarde.parkinglot.auxs.intermeds.VehicleType;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Component
public class ReadFromFileImpl implements ReadFromFile {

    private String filePath;
    private int noOfFloors = 0;

    @Override
    public ParkingLot readParkingLot(String path) {
        this.filePath = path;

        ArrayList<String> floorStrings = extractParkingString();

        ArrayList<ParkingFloor> parkingFloors = generateParkingFloors(floorStrings);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setFloors(parkingFloors);

        return parkingLot;
    }

    @Override
    public List<Vehicle> readVehicles(String path) {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        this.filePath = path;

        ArrayList<String> vehicleStrings = this.extractVehicleString();

        for (String vehicle : vehicleStrings) {
            String[] veh = vehicle.split(" ");
            Vehicle newVehicle = new Vehicle();

            if (veh[0].trim().equals("BIKE")) {
                newVehicle.setVehicleType(VehicleType.valueOf(veh[0].trim()));
            } else if (!veh[0].trim().isEmpty()) {
                newVehicle.setVehicleType(VehicleType.valueOf(veh[0].trim()));
                newVehicle.setPlate(veh[1].trim());
            }

            if (newVehicle.getVehicleType() != null) {
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
            while ((i = input.read()) != -1) {
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private ArrayList<ParkingFloor> generateParkingFloors(ArrayList<String> floorStrings) {
        ArrayList<ParkingFloor> parkingFloors = new ArrayList<>();

        for (String floor : floorStrings) {
            Set<ParkingSpot> totalSpots = new HashSet<>();
            String[] tempCurrentFloor = floor.split(" ");

            ArrayList<String> currentFloor = new ArrayList<>();

            for (String element : tempCurrentFloor) {
                if (!element.trim().isEmpty()) {
                    currentFloor.add(element);
                }
            }

            int floorId = 0;

            if (isNumeric(currentFloor.get(0).trim())) {
                floorId = Integer.parseInt(currentFloor.get(0).trim());
            }

            for (int i = 1; i < currentFloor.size(); i += 2) {
                List<ParkingSpot> parkingSpots = new ArrayList<>();

                if (isNumeric(currentFloor.get(i + 1).trim())
                        && !currentFloor.get(i + 1).trim().isEmpty()) {

                    for (int j = 0; j < Integer.parseInt(currentFloor.get(i + 1).trim()); j++) {
                        String spotType = currentFloor.get(i).trim();

                        ParkingSpot parkingSpot = new ParkingSpot();
                        parkingSpot.setType(SpotType.valueOf(spotType));
                        totalSpots.add(parkingSpot);
                    }
                }
            }

            ParkingFloor parkingFloor = new ParkingFloor();
            parkingFloor.setSpots(totalSpots);

            parkingFloors.add(parkingFloor);
        }

        return parkingFloors;
    }

    private ArrayList<String> extractVehicleString() {
        String[] inputArray = readFromFile(this.filePath).split("\n");
        boolean isVehicles = false;
        ArrayList<String> vehicleStrings = new ArrayList<>();

        for (String element : inputArray) {
            if (element.trim().equals("VehicleStart")) {
                isVehicles = true;
            } else if (element.trim().equals("VehicleEnd")) {
                isVehicles = false;
            } else if (!isNumeric(element) && isVehicles) {
                vehicleStrings.add(element);
            }
        }

        return vehicleStrings;
    }

    private ArrayList<String> extractParkingString() {
        String[] inputArray = readFromFile(this.filePath).split("\n");
        boolean isFloors = false;
        ArrayList<String> floorStrings = new ArrayList<>();

        for (String element : inputArray) {
            if (element.trim().equals("PSTART")) {
                isFloors = true;
            } else if (element.trim().equals("PEND")) {
                isFloors = false;
            } else if (isNumeric(element) && isFloors) {
                this.noOfFloors = Integer.parseInt(element.trim());
            } else if (!isNumeric(element) && isFloors) {
                floorStrings.add(element);
            }
        }

        return floorStrings;
    }

    public boolean isNumeric(String string) {
        string = string.trim();

        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
