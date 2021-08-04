package com.avangarde.parkinglot.InputGenerator;

import com.avangarde.parkinglot.parking.models.SpotType;
import com.avangarde.parkinglot.parking.services.ParkingFloor;
import com.avangarde.parkinglot.parking.services.ParkingLot;
import com.avangarde.parkinglot.vehicle.models.Vehicle;
import com.avangarde.parkinglot.vehicle.services.VehicleFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VehicleGenerator {

    public static final int TWO_CHARACTERS = 2;
    public static final int THREE_CHARACTERS = 3;
    public static final int ASCII_FOR_LETTER_A= 65;
    public static final int ASCII_FOR_LETTER_Z= 90;
    public static final int ASCII_FOR_NUMBER_0= 48;
    public static final int ASCII_FOR_NUMBER_9= 57;
    public static final int LIST_START = 0;

    public List<Vehicle> generateVehicleList(ParkingLot parkingLot) {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (ParkingFloor parkingFloor : parkingLot.getFloors()
        ) {
            for (SpotType spotTypeKey : parkingFloor.getSpotPairs().keySet()
            ) {
                int numberOfSameTypeVehicles = parkingFloor.getSpotPairs().get(spotTypeKey).size();

                int totalVehiclesOnParkingLot = rand(0, numberOfSameTypeVehicles);

                vehicleList.addAll(generateListOfVehicles(spotTypeKey.toString(), totalVehiclesOnParkingLot));
            }
        }
        return vehicleList;
    }

    private List<Vehicle> generateListOfVehicles(String type, int total) {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (int vehicleCount = 0; vehicleCount < total; vehicleCount++) {
            Vehicle vehicle = VehicleFactory.createVehicle(type, createRandomPlate());
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }


    public  String createRandomPlate() {
        String generatedPlateCounty = getRandomCounty();
        String generatedPlateNumber;
        if (generatedPlateCounty.length() == 2) {
            generatedPlateNumber = getRandomContentForPlate(TWO_CHARACTERS, ASCII_FOR_NUMBER_0, ASCII_FOR_NUMBER_9);

        } else {
           generatedPlateNumber = getRandomContentForPlate(THREE_CHARACTERS, ASCII_FOR_NUMBER_0, ASCII_FOR_NUMBER_9);
        }
        String generatedPlateName = getRandomContentForPlate(THREE_CHARACTERS, ASCII_FOR_LETTER_A, ASCII_FOR_LETTER_Z);

        return generatedPlateCounty + generatedPlateNumber + generatedPlateName;
    }

    private  String getRandomContentForPlate(int totalNumberOfCharacters, int startCharacter, int endCharacter) {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(totalNumberOfCharacters);
        for (int i = 0; i < totalNumberOfCharacters; i++) {
            int randomLimitedInt = startCharacter + (int)
                    (random.nextFloat() * (endCharacter - startCharacter + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private static int rand (int min, int max) {
        return (int) (Math.floor((Math.random() * (max-min+1) + min)));
    }

    private String getRandomCounty() {
        List<String> countys = Arrays.asList("AB", "AR", "AG", "BC", "BH", "BN", "BT", "BR", "BV", "BZ", "CL", "CS", "CJ", "CT", "CV", "DB", "DJ", "GL", "GR", "HR",
                "HD", "IL", "IS", "IF", "MM", "MH", "MS", "NT", "OT", "PH", "SJ", "SM", "SB", "SV", "TR", "TM", "TL", "VL", "VS", "VN", "B");
        return countys.get(rand(LIST_START, countys.size() - 1));

    }
}
