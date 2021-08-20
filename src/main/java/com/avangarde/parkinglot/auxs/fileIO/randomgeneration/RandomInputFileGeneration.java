package com.avangarde.parkinglot.auxs.fileIO.randomgeneration;

import com.avangarde.parkinglot.entities.ParkingLot;
import com.avangarde.parkinglot.auxs.fileIO.read.ReadFromFileImpl;
import com.avangarde.parkinglot.auxs.fileIO.write.VehiclesAsStringImpl;
import com.avangarde.parkinglot.auxs.fileIO.Utils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RandomInputFileGeneration {

    private final ApplicationContext ctx;

    public RandomInputFileGeneration(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public void generateFile() {
        ParkingGenerator iFloorBuilder = ctx.getBean(ParkingGenerator.class);

        ReadFromFileImpl inputFile = ctx.getBean(ReadFromFileImpl.class);

        ParkingLot parkingLot = iFloorBuilder.createParkingLot();

        VehiclesAsStringImpl vehicles = ctx.getBean(VehiclesAsStringImpl.class);

        VehicleGenerator vehicleGenerator = ctx.getBean(VehicleGenerator.class);

        inputFile.generate(parkingLot, Utils.resourcesPath() + "input.txt", vehicles.write(vehicleGenerator.generateVehicleList(parkingLot)));
    }
}
