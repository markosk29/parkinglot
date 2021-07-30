package com.avangarde.parkinglot.utils;

import com.avangarde.parkinglot.InputGenerator.IParkingLotAsStringImpl;
import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.vehicle.models.Vehicle;
import com.avangarde.parkinglot.vehicle.services.VehicleBuilder;
import com.avangarde.parkinglot.vehicle.services.VehiclesAsStringImpl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFileImpl implements IInputFile{

    private String filePath;

    @Override
    public ParkingLot readParkingLot(String path) {
        return null;
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
                newVehicle = VehicleBuilder.builder().createVehicle(veh[0].trim(), "N/A");
            } else {
                newVehicle = VehicleBuilder.builder().createVehicle(veh[0].trim(), veh[1].trim());
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
